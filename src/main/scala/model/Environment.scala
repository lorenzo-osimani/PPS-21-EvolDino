package model

trait Environment {

  def temperature: Float

  def vegetationAvailable: Float

  def humidity: Float

  override def toString: String = "T: " + temperature + ", V: " + vegetationAvailable + ", H: " + humidity

  override def equals(obj: Any): Boolean =
    obj match
    case that: Environment =>
      that.isInstanceOf[Environment] &&
      this.humidity == that.humidity &&
      this.vegetationAvailable == that.vegetationAvailable &&
      this.temperature == that.temperature
    case _ => false
}

object Environment {

  def apply(temperature: Float, vegetationAvailable: Float, humidity: Float): Environment =
    new EnvironmentImpl(temperature, vegetationAvailable, humidity)

  def apply(environment: Environment): Environment =
    new EnvironmentImpl(environment.temperature, environment.vegetationAvailable, environment.humidity)

  def evolveFromEnvironment(environment: Environment): Environment =
    new EnvironmentImpl(
      evolveCharacteristic(environment.temperature, 1),
      evolveCharacteristic(environment.vegetationAvailable, 1),
      evolveCharacteristic(environment.humidity, 1))

  val BasicEnvironment: Environment = apply(20, 100, 30)

  val IceAgeEnvironment: Environment = apply(-40, 10, 100)

  private val random = new scala.util.Random

  private def evolveCharacteristic(value: Float, delta: Float) : Float = random.nextFloat() match
    case probability if probability < 0.3 => value - delta
    case probability if probability > (1 - 0.3) => value + delta
    case _ => value

  private class EnvironmentImpl(
                                 override val temperature: Float,
                                 override val vegetationAvailable: Float,
                                 override val humidity: Float
                               ) extends Environment
}