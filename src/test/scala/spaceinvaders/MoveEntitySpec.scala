package spaceinvaders

import org.scalatest.{MustMatchers, WordSpec}

class MoveEntitySpec extends WordSpec with MustMatchers {


  "moveMissile" must {

    "change the index of missile by the amount of its speed" in {
      val missile = new Missile(35, -10)

      missile.getIndex mustEqual 35
      MoveEntity.moveMissile(missile)
      missile.getIndex mustEqual 25
    }
  }

  "moveShip" must {

    "change the index of ship by the amount of its speed" in{
     val ship = new Ship(1, 1)

      ship.getIndex mustEqual 1
      MoveEntity.moveShip(ship)
      ship.getIndex mustEqual 2
    }

    "move the ship to a new line on index 9" in {
      val ship = new Ship(9, 1)

      ship.getIndex mustEqual 9
      MoveEntity.moveShip(ship)
      ship.getIndex mustEqual 19
    }

    "move the ship to a new line on index 10 and travelling left" in {
      val ship = new Ship(10, -1)

      ship.getIndex mustEqual 10
      MoveEntity.moveShip(ship)
      ship.getIndex mustEqual 20
    }

    "invert the ship's speed when changes line going right" in {
      val ship = new Ship(9, 1)

      ship.getSpeed mustEqual 1
      MoveEntity.moveShip(ship)
      ship.getSpeed mustEqual -1
    }

    "invert the ship's speed when changes line going left" in {
      val ship = new Ship(10, -1)

      ship.getSpeed mustEqual -1
      MoveEntity.moveShip(ship)
      ship.getSpeed mustEqual 1
    }

  }

  "willChangeLine" must {

    "return false " in {
      val result = MoveEntity.willChangeLines(5, 1)

      result mustEqual false
    }

    "return true going right" in {
      val result = MoveEntity.willChangeLines(9, 1)

      result mustEqual true
    }

    "return true going left" in {
      val result = MoveEntity.willChangeLines(10, -1)

      result mustEqual true
    }
  }

  "collisionDetect" must {

    "leave ships that have not been hit" in {
      val ship = new Ship(15, 2)
      val missile = new Missile(5, -10)

      val fakeBoard = List(ship, missile)

      MoveEntity.collisionDetect(fakeBoard).contains(ship) mustEqual true
    }

    "leave missiles that have not hit anything" in {
      val ship = new Ship(15, 2)
      val missile = new Missile(5, -10)

      val fakeBoard = List(ship, missile)

      MoveEntity.collisionDetect(fakeBoard).contains(ship) mustEqual true
    }


    "ignore ships that should have collided at the same index" in {
      val sharedIndex = 15
      val ship = new Ship(sharedIndex, 2)
      val ship2 = new Ship(sharedIndex, -10)

      val fakeBoard = List(ship, ship2)

      MoveEntity.collisionDetect(fakeBoard).contains(ship) mustEqual true
      MoveEntity.collisionDetect(fakeBoard).contains(ship2) mustEqual true
    }

    "remove ships that share an index with a missile" in {
      val sharedIndex = 5
      val ship = new Ship(sharedIndex, 2)
      val missile = new Missile(sharedIndex, -10)

      val fakeBoard = List(ship, missile)

      MoveEntity.collisionDetect(fakeBoard).contains(ship) mustEqual false
    }

    "remove missiles that share an index with a ship" in {
      val sharedIndex = 5
      val ship = new Ship(sharedIndex, 2)
      val missile = new Missile(sharedIndex, -10)

      val fakeBoard = List(ship, missile)

      MoveEntity.collisionDetect(fakeBoard).contains(missile) mustEqual false
    }

    "add an explosion" in {
      val sharedIndex = 5
      val ship = new Ship(sharedIndex, 2)
      val missile = new Missile(sharedIndex, -10)
      val explosion = new Explosion(sharedIndex)

      val fakeBoard = List(ship, missile)

      MoveEntity.collisionDetect(fakeBoard).map(_.getName).contains(explosion.getName) mustEqual true
    }

    "add an explosion where the collision happened" in {
      val sharedIndex = 5
      val ship = new Ship(sharedIndex, 2)
      val missile = new Missile(sharedIndex, -10)
      val explosion = new Explosion(sharedIndex)

      val fakeBoard = List(ship, missile)

      val newBoard = MoveEntity.collisionDetect(fakeBoard)
      newBoard.map(e => (e.getName, e.getIndex)).contains((explosion.getName, explosion.getIndex)) mustEqual true
    }
  }
}
