package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.controller.engine.EngineController.{
  disasterFunction,
  environmentEvolutionFunction
}
import it.unibo.pps.evoldino.model.dinosaur.{ Population, PopulationFactory }

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld(Environment.BasicEnvironment, Seq.empty)

  def resetHistory(
      environment: Environment = Environment.BasicEnvironment,
      population: Population = PopulationFactory(50)): Unit =
    history = initializeWorld(environment, population)

  private def initializeWorld(
      startingEnvironment: Environment,
      startingPopulation: Population): History =
    Seq(WorldSnapshot(1, startingEnvironment, startingPopulation))

  def getLastSnapshot(): WorldSnapshot = history.head

  def getLastLivingPopulation(): Population = getLastSnapshot().livingPopulation()

  def nextIteration(): Unit =
    val newPopulation = getLastLivingPopulation()
    getLastSnapshot().closeSnapShot()
    newPopulation foreach (_.incrementAge())
    history = WorldSnapshot(
      history.size+1,
      environmentEvolutionFunction()(getLastSnapshot().environment),
      newPopulation,
      disasterFunction()()
    ) +: history

  def dinosaursEatingPhase(): Unit =
    val difference =
      getLastSnapshot().environment.vegetationAvailable / getLastLivingPopulation().size
    if (difference < 1)
      getLastLivingPopulation()
        .sorted(_.age - _.age)
        .take(((1 - difference) * getLastLivingPopulation().size).toInt)
        .foreach(_.kill())

  def applyDisturbances(): Unit =
    for dino <- getLastLivingPopulation()
    yield Environment.applyEnvironmentDamage(dino, getLastSnapshot().environment)
    getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation().toList))

  def reproductionPhase(): Unit =
    getLastSnapshot().population.foreach(_.moveDinosaur())
    getLastSnapshot().population = PopulationFactory.reproduction(getLastSnapshot().livingPopulation())

  def isSimulationOver(): Boolean =
    history.size >= EngineConstants.max_iterations ||
      getLastLivingPopulation().size >= EngineConstants.max_population_size || getLastLivingPopulation().size <= 0
}
