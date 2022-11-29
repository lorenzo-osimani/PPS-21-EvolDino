package it.unibo.pps.evoldino.controller.engine

import EngineController.*
import it.unibo.pps.evoldino.model.dinosaur.{ Population, PopulationFactory, Reproduction }
import it.unibo.pps.evoldino.model.world.{ Environment, WorldSnapshot }
import it.unibo.pps.evoldino.model.disaster.Disaster

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld(Environment.BasicEnvironment, Seq.empty)

  def resetHistory(
      environment: Environment = Environment.BasicEnvironment,
      population: Population = PopulationFactory(10)): Unit =
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
    history = WorldSnapshot(
      environmentEvolutionFunction()(getLastSnapshot().environment),
      newPopulation,
      disasterFunction()()
    ) +: history

  def dinosaursEatingPhase(): Unit =
    val difference =
      getLastSnapshot().environment.vegetationAvailable / getLastLivingPopulation().size
    println(
      getLastSnapshot().environment.vegetationAvailable + " " + getLastLivingPopulation().size
    )
    if (difference < 1)
      getLastLivingPopulation()
        .sorted(_.age - _.age)
        .take(((1 - difference) * getLastLivingPopulation().size).toInt)
        .foreach(_.kill())

  def applyDisturbances(): Unit =
    getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation().toList))

  def reproductionPhase(): Unit =
    getLastSnapshot().population = Reproduction.reproduction(getLastSnapshot().livingPopulation())

  def isSimulationOver(): Boolean =
    history.size >= EngineConstants.max_iterations ||
      getLastLivingPopulation().size >= EngineConstants.max_population_size || getLastLivingPopulation().size <= 0
}
