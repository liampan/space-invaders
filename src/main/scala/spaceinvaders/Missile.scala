package spaceinvaders

class Missile(private var index: Int, private var speed: Int) extends Entity{

  override def getName: String = "missile"

  override def getSpeed: Int = speed

  override def getIndex: Int = index

  override def getLook: String = ItemLook.missile

  override def setIndex(newIndex: Int): Unit = {
    this.index = newIndex
  }

  override def setBoth(newIndex: Int, newSpeed: Int): Unit = ???

}
