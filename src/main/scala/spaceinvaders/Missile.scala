package spaceinvaders

case class Missile(index: Int, speed: Int) extends Thing{

  private var missileIndex = index

  private val missileSpeed = speed

  override def getName: String = "missile"

  override def getSpeed: Int = missileSpeed

  override def getIndex: Int = index

  override def getLook: String = ItemLook.missile

  override def setIndex(newIndex: Int): Unit = {
    this.missileIndex = newIndex
  }

}
