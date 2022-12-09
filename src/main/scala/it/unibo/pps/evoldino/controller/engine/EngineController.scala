package it.unibo.pps.evoldino.controller.engine

import it.unibo.pps.evoldino.model.disaster.{ ClimateEffect, Disaster, DisasterGenerator }
import it.unibo.pps.evoldino.model.world.Environment

object EngineController {
  private var manual: Boolean = false

  private var manual_temperature: Float = Environment.BasicEnvironment.temperature
  private var manual_humidity: Float = Environment.BasicEnvironment.humidity
  private var manual_vegetation_percentage: Float = Environment.BasicEnvironment.vegetationAvailable

  var incomingDisasters: List[Disaster] = List.empty

  def resetController() =
    manual = false
    incomingDisasters = List.empty
    modifyManualSettings(
      Environment.BasicEnvironment.temperature,
      Environment.BasicEnvironment.humidity,
      Environment.BasicEnvironment.vegetationAvailable
    )

  def setManualMode(mode: Boolean) = manual = mode
  def isManualModeActive() = manual

  def environmentEvolutionFunction(): Environment => Environment =
    if (manual) { (_) =>
      Environment(manual_temperature, manual_humidity, manual_vegetation_percentage)
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
    var disasters: Seq[Disaster] = Seq.empty
    if (manual) disasters = Seq.from(incomingDisasters)
    else
      disasters = DisasterGenerator.createListOfDisastersWithDistribuition()
    incomingDisasters = List.empty
    () => disasters

  def addDisaster(disaster: Disaster): Unit =
    incomingDisasters = incomingDisasters :+ disaster

}
