package spaceinvaders

import spaceinvaders.Printer._

object Game extends App {

  val bS : Int = 10

  val shipInput: List[Entity] = List(new Ship(3, 1))

  def parse(board: List[Entity]): List[Entity] ={
    board.foreach{ t =>
        t.getName match{
          case "ship"    => moveShip(t)
          case "missile" => moveMissile(t)
      }
    }
    collisionDetect(board)
  }

  def moveMissile(missile: Entity): Unit ={
    val newIndex = missile.getIndex+missile.getSpeed
    missile.setIndex(newIndex)
  }

  def moveShip(ship: Entity): Unit ={

    val newIndex = if(willChangeLines(ship.getIndex, ship.getSpeed)) {
      moveDownLine(ship.getIndex, ship.getSpeed)
    }else ship.getIndex + ship.getSpeed

    val newSpeed = if (willChangeLines(ship.getIndex, ship.getSpeed)) {
      -ship.getSpeed
    } else ship.getSpeed
    ship.setBoth(newIndex, newSpeed)
  }

  def moveDownLine(index: Int, speed: Int): Int = {
    val currentLineNumber = index/10
    if (speed > 0){
      val movesToEndOfLine = 9- (index%10)
      val nextEndOfLine = (currentLineNumber+1)*10+9
      val newIndex = nextEndOfLine - (speed - movesToEndOfLine) +1
      newIndex
    }
    else {
      val movesToEndOfLine = index%10
      val nextStartOfLine = (currentLineNumber+1)*10
      val newIndex = nextStartOfLine - (speed + movesToEndOfLine) -1
      newIndex
    }
  }

  def willChangeLines(index: Int, speed: Int): Boolean ={
    val newPos = index + speed
    if (newPos < 0) return true
    val currentLineNumber = index/10
    val newLineNumber = newPos/10
    newLineNumber != currentLineNumber
  }

  def collisionDetect(board: List[Entity]): List[Entity] ={
    val shipsIndexes: Set[Int] = board.filter(x => x.getName == "ship").map(s => s.getIndex).toSet
    val missilesIndexes: Set[Int] = board.filter(x => x.getName == "missile").map(m => m.getIndex).toSet
    val collisions = shipsIndexes.intersect(missilesIndexes)
    val survivingShips = board.filterNot(s => collisions.contains(s.getIndex))
      .filterNot(s => s.getName == "missile" && s.getIndex == -5)
    survivingShips
  }

  def loop(board: List[Entity]): Unit ={

    def loopHelper(board: List[Entity], count: Int) {
      printBoard(board, bS)
      println(board)
      val next = parse(board)
      Thread.sleep(1000)
      loopHelper(if (count%2 == 1){next ++ List(new Missile(85, -10))} else next, count+1)
    }

    loopHelper(board, 0)
  }

  loop(shipInput)
}
