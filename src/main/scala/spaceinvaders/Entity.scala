package spaceinvaders

trait Entity {

  def getName: String

  def getLook: String

  def getSpeed: Int

  def getIndex: Int

  def setIndex(newIndex: Int)

  def setBoth(newIndex: Int, newSpeed: Int)

}
