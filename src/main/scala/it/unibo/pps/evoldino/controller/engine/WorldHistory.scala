package it.unibo.pps.evoldino.controller.engine

import WorldSnapshot.Population
import EngineConstants.*
import it.unibo.pps.evoldino.model.{ Disaster, Environment }

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld()

  var disturbances: List[Disaster] = List.empty

  def resetHistory(
      environment: Environment = Environment.BasicEnvironment,
      population: Population = Seq.fill(20)(1)): Unit =
    history = initializeWorld(environment, population)

  private def initializeWorld(
      startingEnvironment: Environment = Environment.BasicEnvironment,
      startingPopulation: Population = Seq.fill(20)(1)): History =
    Seq(WorldSnapshot(startingEnvironment, startingPopulation))

  def getLastSnapshot(): WorldSnapshot = history.head

  def dinosaursEatingPhase(): Unit =
    val damage =
      1 - getLastSnapshot().environment.vegetationAvailable * dino_veg_ratio / getLastSnapshot().population.size
    if (damage > 0) getLastSnapshot().damagePopulation(damage)

  def applyDisturbances(): Unit =
    return

  //for disturbance <- disturbances do
  //val disturbance = disturbances.drop(i)
  //disturbance.apply(getLastSnapshot().livingPopulation())

  def nextIteration(): Unit =
    history = WorldSnapshot(
      environmentEvolutionPhase(),
      getLastSnapshot().population //.map(d => d.incrementAge())
    ) +: history

  def reproductionPhase(): Unit =
    getLastSnapshot().population =
      getLastSnapshot().population //reproduction(getLastSnapshot().population)
    getLastSnapshot().closeSnapShot()

  def isSimulationOver(): Boolean =
    history.size >= max_iterations ||
      getLastSnapshot().population.size >= max_population_size ||
      getLastSnapshot().population.size <= 0

  def addDisaster(disaster: Disaster): Unit =
    disturbances = disaster +: disturbances

  private def environmentEvolutionPhase(): Environment =
    Environment.evolveFromEnvironment(getLastSnapshot().environment)
}
