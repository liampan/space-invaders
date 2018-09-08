package spaceinvaders

class Ship(private var index: Int, private var speed: Int) extends Entity {

  private var shipLook = "-"

  override def getName: String = "ship"

  override def getSpeed: Int = this.speed

  override def getIndex: Int = this.index

  override def getLook = {
    val s = this.getSpeed
    shipLook  = {
      if (s <= -5) ItemLook.ship_left_fast
      else if (s < 0) ItemLook.ship_left
      else if (s <= 5) ItemLook.ship_right
      else ItemLook.ship_right_fast
    }
    shipLook
  }

  def setSpeed(newSpeed: Int) = {
    this.speed = newSpeed
  }

  override def setIndex(newIndex: Int) = {
    this.index = newIndex
  }

  def setBoth(newIndex: Int, newSpeed: Int) = {
    this.setIndex(newIndex)
    this.setSpeed(newSpeed)
  }

}
