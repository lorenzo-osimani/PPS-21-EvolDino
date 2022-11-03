package model

trait Environment {

  def temperature: Float

  def vegetationAvailable: Float

  def humidity: Float
}

object Environment {

  def apply(temperature: Float, vegetationAvailable: Float, humidity: Float): Environment =
    new EnvironmentImpl(temperature, vegetationAvailable, humidity)

  val BasicEnvironment: Environment = apply(20, 100, 30)

  val IceAgeEnvironment: Environment = apply(-40, 10, 100)

  private class EnvironmentImpl(
                                 override val temperature: Float,
                                 override val vegetationAvailable: Float,
                                 override val humidity: Float
                               ) extends Environment
}