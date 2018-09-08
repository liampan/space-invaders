package spaceinvaders

import spaceinvaders.Printer._
import spaceinvaders.MoveEntity.{collisionDetect, moveShip, moveMissile}

object Game extends App {

  val boardSize : Int = 10

  val shipInput: List[Entity] = List(new Ship(3, 1))

  def parse(board: List[Entity]): List[Entity] ={
    board.foreach{ t =>
        t.getName match {
          case "ship"      => moveShip(t)
          case "missile"   => moveMissile(t)
          case "explosion" => t.setIndex(-1)
      }
    }
    collisionDetect(board)
  }

  def loop(board: List[Entity]): Unit ={

    def loopHelper(board: List[Entity], count: Int) {
      printBoard(board, boardSize)
      val next = parse(board)
      Thread.sleep(500)
      loopHelper(if (count/2%2 == 0){next ++ List(new Missile(85, -10))} else next, count+1)
    }

    loopHelper(board, 0)
  }

  loop(shipInput)

}
