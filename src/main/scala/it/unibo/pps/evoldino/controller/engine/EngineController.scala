package it.unibo.pps.evoldino.controller.engine

import it.unibo.pps.evoldino.model.{ Disaster, Environment }

object EngineController {
  private var manual: Boolean = false

  private var manual_temperature: Float = 20
  private var manual_humidity: Float = 50
  private var manual_vegetation_percentage: Float = 100

  var incomingDisasters: List[Disaster] = List.empty

  def setManualMode(mode: Boolean) = manual = mode

  def environmentEvolutionFunction(): Environment => Environment =
    if (manual) { (env: Environment) =>
      Environment(manual_temperature, manual_humidity, manual_humidity)
    } else { (env: Environment) =>
      Environment.evolveFromEnvironment(env)
    }

  def modifyManualSettings(
      temp: Float = manual_temperature,
      hum: Float = manual_humidity,
      veg: Float = manual_vegetation_percentage) = {
    manual_temperature = temp
    manual_humidity = hum
    manual_vegetation_percentage = veg
  }

  def disasterFunction(): () => Seq[Disaster] =
    if (manual) { () =>
      val disasters = Seq.from(incomingDisasters)
      incomingDisasters = incomingDisasters.filter(_ => false)
      disasters
    } else { () =>
      //generateRandomDisasters()
      Seq.empty
    }

  def addDisaster(disaster: Disaster): Unit =
    incomingDisasters = incomingDisasters :+ disaster

}
