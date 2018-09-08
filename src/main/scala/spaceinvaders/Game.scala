package spaceinvaders

import spaceinvaders.Printer._

object Game extends App {


  val bS : Int = 10

  val shipInput: List[Thing] = List(Ship(3, 1), Missile(85, -10))

  def parse(board: List[Thing]): List[Thing] = {
    val moved = board.map{ t =>
        t.getName match{
          case "ship"    => moveShip(Ship(t.getIndex,t.getSpeed))
          case "missile" => moveMissile(Missile(t.getIndex,t.getSpeed))
      }
    }
    collisionDetect(moved)
  }

  def moveMissile(missile: Missile): Missile = {
    //missile.setIndex(missile.getIndex+missile.getSpeed)
    Missile(missile.getIndex+missile.getSpeed , missile.getSpeed)
  }

  def moveShip(ship: Ship): Ship ={

    val newIndex = if(willChangeLines(ship.getIndex, ship.getSpeed)) {
      moveDownLine(ship.getIndex, ship.getSpeed)
    }else ship.getIndex + ship.getSpeed

    val newSpeed = if (willChangeLines(ship.getIndex, ship.getSpeed)) {
      -ship.getSpeed
    } else ship.getSpeed

    Ship(newIndex, newSpeed)
  }

  def moveDownLine(index: Int, value: Int) :Int= {
    val currentLineNumber = index/10
    if (value > 0){
      val movesToEndOfLine = 9- (index%10)
      val nextEndOfLine = (currentLineNumber+1)*10+9
      val newIndex = nextEndOfLine - (value - movesToEndOfLine) +1
      newIndex
    }
    else {
      val movesToEndOfLine = index%10
      val nextStartOfLine = (currentLineNumber+1)*10
      val newIndex = nextStartOfLine - (value + movesToEndOfLine) -1
      newIndex
    }
  }

  def willChangeLines(index: Int, speed: Int): Boolean = {
    val newPos = index + speed
    if (newPos < 0) return true
    val currentLineNumber = index/10
    val newLineNumber = newPos/10
    newLineNumber != currentLineNumber
  }

  def collisionDetect(board: List[Thing]): List[Thing] = {
    val shipsIndexes: Set[Int] = board.filter(x => x.getName == "ship").map(s => s.getIndex).toSet
    val missilesIndexes: Set[Int] = board.filter(x => x.getName == "missile").map(m => m.getIndex).toSet
    val collisions = shipsIndexes.intersect(missilesIndexes)
    board.filterNot(s => collisions.contains(s.getIndex)).filterNot(s => s == Missile(5 , -10))
  }

  def loop(board: List[Thing]): Unit = {

    def loopHelper(board: List[Thing], count: Int) {
      printBoard(board, bS)
      val next = parse(board)
      Thread.sleep(1000)
      loopHelper(if (count%2 == 0){next ++ List(Missile(85, -10))} else next, count+1)
    }

    loopHelper(board, 0)
  }

  loop(shipInput)
}