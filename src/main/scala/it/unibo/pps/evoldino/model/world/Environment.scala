package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.model.world.WorldConstants.*
import it.unibo.pps.evoldino.utils.GlobalUtils.calculateProbability

trait Environment {

  def temperature: Float

  def vegetationAvailable: Float

  def humidity: Float

  override def toString: String =
    "T: " + temperature + ", V: " + vegetationAvailable + ", H: " + humidity

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

  def apply(
      temperature: Float = BasicEnvironment.temperature,
      vegetationAvailable: Float = BasicEnvironment.vegetationAvailable,
      humidity: Float = BasicEnvironment.humidity): Environment =
    new EnvironmentImpl(temperature, vegetationAvailable, humidity)

  def apply(environment: Environment): Environment =
    new EnvironmentImpl(
      environment.temperature,
      environment.vegetationAvailable,
      environment.humidity
    )

  import it.unibo.pps.evoldino.utils.PimpScala.given

  def evolveFromEnvironment(environment: Environment): Environment =
    new EnvironmentImpl(
      evolveCharacteristic(environment.temperature)
        keepValueInBounds (min_temperature, max_temperature),
      evolveCharacteristic(environment.vegetationAvailable)
        keepValueInBounds (max = max_vegetation_percentage),
      evolveCharacteristic(environment.humidity) keepValueInBounds (min_humidity, max_humidity)
    )

  val BasicEnvironment: Environment = apply(20, 50, 30)

  val IceAgeEnvironment: Environment = apply(-40, 10, 100)

  private def evolveCharacteristic(value: Float): Float = value match
    case _ if calculateProbability(characteristicEvolutionProbability) =>
      value - environmentEvolutionDelta
    case _ if calculateProbability(characteristicEvolutionProbability) =>
      value + environmentEvolutionDelta
    case _ => value

  private class EnvironmentImpl(
      override val temperature: Float,
      override val vegetationAvailable: Float,
      override val humidity: Float)
      extends Environment
}
