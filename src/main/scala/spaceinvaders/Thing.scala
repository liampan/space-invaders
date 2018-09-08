package spaceinvaders

trait Thing {

  def getName: String

  def getLook: String

  def getSpeed: Int

  def getIndex: Int

  def setIndex(newIndex: Int)

}
