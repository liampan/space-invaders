package spaceinvaders

object Printer {

  def printBoard(board: List[Entity], bS: Int): Unit = {
    val printable = boardConverter(board, bS)
    println("┏" + "━" * bS * 2 + "━┓")
    printable.foreach(row => {
      print("┃ ")
      row.foreach(cell => {
        print(s"${if (cell._2 == 0) s"${Console.BLACK}.${Console.RESET}" else cell._3} ")
      })
      println("┃")
    })
    println("┗" + "━" * bS * 2 + "━┛")
  }

  def boardConverter(things: List[Entity], bS: Int): List[List[(Int, Int, String)]] = {
    val blank = List.range(0, 100).map(i => (i, 0, "blank"))
    val steps = List.range(bS, bS*bS+bS, bS)
    val filled: List[(Int, Int, String)] = blank.map(s =>  if(things.map(_.getIndex).contains(s._1)) {
      val thing = things(things.indexWhere(p => p.getIndex == s._1))
      (thing.getIndex, thing.getSpeed, thing.getLook)
      } else s)
    steps.map(s => filled.slice(0, s).takeRight(bS))
  }
  
}
