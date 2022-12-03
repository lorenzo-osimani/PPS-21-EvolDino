package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.controller.engine.EngineController.{
  disasterFunction,
  environmentEvolutionFunction,
  manual_temperature
}
import it.unibo.pps.evoldino.model.dinosaur.{ Population, PopulationFactory, Reproduction }

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
    if (difference < 1)
      getLastLivingPopulation()
        .sorted(_.age - _.age)
        .take(((1 - difference) * getLastLivingPopulation().size).toInt)
        .foreach(_.kill())

  private def applyEnvironmentDamage(environment: Environment): Unit = for {
    dino <- getLastLivingPopulation()
  } yield {
    val delta =
      (dino.genes.idealHumidity - environment.humidity).abs + (dino.genes.idealTemperature - environment.temperature).abs
    dino.damageDinosaur(math.pow(delta / 10, 2))
  }

  def applyDisturbances(): Unit =
    applyEnvironmentDamage(getLastSnapshot().environment)
    getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation().toList))

  def reproductionPhase(): Unit =
    getLastSnapshot().population.foreach(_.moveDinosaur())
    getLastSnapshot().population = Reproduction.reproduction(getLastSnapshot().livingPopulation())

  def isSimulationOver(): Boolean =
    history.size >= EngineConstants.max_iterations ||
      getLastLivingPopulation().size >= EngineConstants.max_population_size || getLastLivingPopulation().size <= 0
}
