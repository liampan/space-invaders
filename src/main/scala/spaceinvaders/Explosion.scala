package spaceinvaders

class Explosion(private var index : Int) extends Entity {

  override def getIndex: Int = index

  override def getLook: String = ItemLook.explosion

  override def getName: String = "explosion"

  override def setBoth(newIndex: Int, newSpeed: Int): Unit = ???

  override def setIndex(newIndex: Int): Unit = this.index = newIndex

  override def getSpeed: Int = -1

}
