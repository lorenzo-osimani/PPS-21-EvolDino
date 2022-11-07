package model

import model.GlobalUtils._
import EnvironmentConstants._

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

  def evolveFromEnvironment(environment: Environment): Environment =
    new EnvironmentImpl(
      evolveCharacteristic(environment.temperature, evolutionDelta),
      evolveCharacteristic(environment.vegetationAvailable, evolutionDelta),
      evolveCharacteristic(environment.humidity, evolutionDelta)
    )

  val BasicEnvironment: Environment = apply(20, 100, 30)

  val IceAgeEnvironment: Environment = apply(-40, 10, 100)

  private def evolveCharacteristic(value: Float, delta: Float): Float = value match
    case _ if calculateProbability(characteristicEvolutionProbability) => value - delta
    case _ if calculateProbability(characteristicEvolutionProbability) => value + delta
    case _                                                             => value

  private class EnvironmentImpl(
      override val temperature: Float,
      override val vegetationAvailable: Float,
      override val humidity: Float)
      extends Environment
}

object EnvironmentConstants {

  val characteristicEvolutionProbability = 0.3

  val evolutionDelta = 1
}
