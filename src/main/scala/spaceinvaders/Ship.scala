package spaceinvaders

case class Ship(index: Int, speed: Int) extends Thing {

  private var shipSpeed = speed

  private var shipIndex = index

  override def getName: String = "ship"

  override def getSpeed: Int = shipSpeed

  override def getIndex: Int = shipIndex

  override def getLook = {
    val s = this.speed
    if (s <= -5) ItemLook.ship_left_fast
    else if (s < 0) ItemLook.ship_left
    else if (s <= 5) ItemLook.ship_right
    else ItemLook.ship_right_fast
  }

  override def setIndex(newIndex: Int) = {
    this.shipIndex = newIndex
  }

  def setSpeed(newSpeed: Int) = {
    this.shipSpeed = newSpeed
  }

  def setBoth(newIndex: Int, newSpeed: Int) = {
    this.shipIndex = newIndex
    this.shipSpeed = newSpeed
  }

}
