package it.unibo.pps.evoldino.controller.engine

import WorldSnapshot.Population
import EngineConstants.*
import it.unibo.pps.evoldino.model.{ Disaster, Environment }

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld(Environment.BasicEnvironment, Seq.empty)

  var incomingDisasters: List[Disaster] = List.empty

  def resetHistory(
      environment: Environment = Environment.BasicEnvironment,
      population: Population = Seq.empty): Unit =
    history = initializeWorld(environment, population)

  private def initializeWorld(
      startingEnvironment: Environment,
      startingPopulation: Population): History =
    Seq(WorldSnapshot(startingEnvironment, startingPopulation))

  def getLastSnapshot(): WorldSnapshot = history.head

  def getLastLivingPopulation(): Population = getLastSnapshot().livingPopulation()

  def nextIteration(): Unit =
    val newPopulation = getLastLivingPopulation()
    getLastSnapshot().closeSnapShot()
    newPopulation foreach (_.increaseAge())
    history =
      WorldSnapshot(environmentEvolutionPhase(), newPopulation, incomingDisasters) +: history
    incomingDisasters = incomingDisasters filter (_ => false)

  def dinosaursEatingPhase(): Unit =
    val damage =
      1 - getLastSnapshot().environment.vegetationAvailable * dino_veg_ratio / getLastLivingPopulation().size
    if (damage > 0) getLastSnapshot().damagePopulation(damage)

  def addDisaster(disaster: Disaster): Unit =
    incomingDisasters = incomingDisasters :+ disaster

  def applyDisturbances(): Unit =
    // getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation()))
    return

  def reproductionPhase(): Unit =
    getLastSnapshot().population =
      getLastSnapshot().population //reproduction(getLastSnapshot().population)

  def isSimulationOver(): Boolean =
    history.size >= max_iterations ||
      getLastLivingPopulation().size >= max_population_size ||
      getLastLivingPopulation().size <= 0

  private def environmentEvolutionPhase(): Environment =
    Environment.evolveFromEnvironment(getLastSnapshot().environment)
}
