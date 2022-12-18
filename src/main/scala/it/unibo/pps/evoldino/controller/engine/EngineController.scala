package it.unibo.pps.evoldino.controller.engine

import it.unibo.pps.evoldino.model.disaster.{ Disaster, DisasterGenerator }
import it.unibo.pps.evoldino.model.world.Environment

object EngineController:
  private var manual: Boolean = false
  private var manual_temperature: Float = Environment.BasicEnvironment.temperature
  private var manual_humidity: Float = Environment.BasicEnvironment.humidity
  private var manual_vegetation_percentage: Float = Environment.BasicEnvironment.vegetationAvailable

  var incomingDisasters: List[Disaster] = List.empty

  /**Reset all the parameters of this object*/
  def resetController(): Unit =
    manual = false
    incomingDisasters = List.empty
    modifyManualSettings(
      Environment.BasicEnvironment.temperature,
      Environment.BasicEnvironment.humidity,
      Environment.BasicEnvironment.vegetationAvailable
    )

  /**Set the manual mode activation*/
  def setManualMode(mode: Boolean): Unit = manual = mode
  /**Returns true if the manual mode is active*/
  def isManualModeActive: Boolean = manual

  /**Generate the function to get the new environment given the previous one*/
  def environmentEvolutionFunction(): Environment => Environment =
    if (manual)
      _ => Environment(manual_temperature, manual_humidity, manual_vegetation_percentage)
    else
      (env: Environment) => Environment.evolveFromEnvironment(env)

  /**Modify manually the environment*/
  def modifyManualSettings(
      temp: Float = manual_temperature,
      hum: Float = manual_humidity,
      veg: Float = manual_vegetation_percentage): Unit =
    manual_temperature = temp
    manual_humidity = hum
    manual_vegetation_percentage = veg

  /**Generate the function to get the new list of disasters to apply*/
  def disasterFunction(): () => Seq[Disaster] =
    var disasters: Seq[Disaster] = Seq.empty
    if (manual) disasters = Seq.from(incomingDisasters)
    else
      disasters = DisasterGenerator.createListOfDisastersWithDistribuition()
    incomingDisasters = List.empty
    () => disasters

  /**Cause manually a disaster in the next iteration*/
  def addDisaster(disaster: Disaster): Unit =
    incomingDisasters = incomingDisasters :+ disaster
