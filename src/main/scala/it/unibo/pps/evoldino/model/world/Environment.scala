package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.model.dinosaur.{ Dinosaur, Population }
import it.unibo.pps.evoldino.model.world.WorldConstants.*
import it.unibo.pps.evoldino.utils.GlobalUtils.calculateProbability

trait Environment {

  def temperature: Float

  def humidity: Float

  def vegetationAvailable: Float

  override def toString: String =
    "T: " + temperature + ", H: " + humidity + ", V: " + vegetationAvailable

  override def equals(obj: Any): Boolean =
    obj match
      case that: Environment =>
        that.isInstanceOf[Environment] &&
        this.temperature == that.temperature &&
        this.humidity == that.humidity &&
        this.vegetationAvailable == that.vegetationAvailable
      case _ => false
}

object Environment {

  def apply(
      temperature: Float = BasicEnvironment.temperature,
      humidity: Float = BasicEnvironment.humidity,
      vegetationAvailable: Float = BasicEnvironment.vegetationAvailable): Environment =
    new EnvironmentImpl(temperature, humidity, vegetationAvailable)

  def apply(environment: Environment): Environment =
    new EnvironmentImpl(
      environment.temperature,
      environment.humidity,
      environment.vegetationAvailable
    )

  import it.unibo.pps.evoldino.utils.PimpScala.given

  def evolveFromEnvironment(environment: Environment): Environment =
    Environment(
      evolveCharacteristic(environment.temperature)
        keepValueInBounds (min_temperature, max_temperature),
      evolveCharacteristic(environment.humidity) keepValueInBounds (min_humidity, max_humidity),
      evolveCharacteristic(environment.vegetationAvailable)
        keepValueInBounds (max = max_vegetation_value)
    )

  def applyEnvironmentDamage(dino: Dinosaur, environment: Environment): Unit =
    val delta =
      (dino.genes.idealHumidity - environment.humidity).abs + (dino.genes.idealTemperature - environment.temperature).abs
    dino.damageDinosaur(math.pow(delta / 10, 2))

  val BasicEnvironment: Environment = apply(20, 30, 100)

  val IceAgeEnvironment: Environment = apply(-40, 100, 10)
  val DroughtEnvironment: Environment = apply(60, 10, 10)

  private def evolveCharacteristic(value: Float): Float = value match
    case _ if calculateProbability(characteristicEvolutionProbability) =>
      value - environmentEvolutionDelta
    case _ if calculateProbability(characteristicEvolutionProbability) =>
      value + environmentEvolutionDelta
    case _ => value

  private class EnvironmentImpl(
      override val temperature: Float,
      override val humidity: Float,
      override val vegetationAvailable: Float)
      extends Environment
}
