package it.unibo.pps.evoldino.controller.engine

import EngineConstants.*
import EngineController.*
import it.unibo.pps.evoldino.model.dinosaur.Reproduction
import it.unibo.pps.evoldino.model.world.{Environment, WorldSnapshot}
import it.unibo.pps.evoldino.model.world.Population
import it.unibo.pps.evoldino.model.disaster.Disaster

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld(Environment.BasicEnvironment, Seq.empty)

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
    newPopulation foreach (_.incrementAge())
    environmentEvolutionFunction()(getLastSnapshot().environment).toString
    history = WorldSnapshot(
      environmentEvolutionFunction()(getLastSnapshot().environment),
      newPopulation,
      disasterFunction()()
    ) +: history

  def dinosaursEatingPhase(): Unit =
    val percentage =
      1 - getLastSnapshot().environment.vegetationAvailable * dino_veg_ratio / getLastLivingPopulation().size
    if (percentage > 0) getLastSnapshot().damagePopulation(percentage, 20)

  def applyDisturbances(): Unit =
    getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation().toList))

  def reproductionPhase(): Unit =
    getLastSnapshot().population = Reproduction.reproduction(getLastSnapshot().livingPopulation())

  def isSimulationOver(): Boolean =
    history.size >= max_iterations ||
      getLastLivingPopulation().size >= max_population_size // || getLastLivingPopulation().size <= 0
}
