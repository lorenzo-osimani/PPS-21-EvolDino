package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.controller.engine.EngineController.{
  disasterFunction,
  environmentEvolutionFunction
}
import it.unibo.pps.evoldino.model.dinosaur.{ Population, PopulationFactory }

/** Represent the full history of a single simulation */
object WorldHistory:

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld(Environment.BasicEnvironment, Seq.empty)

  /** Reset the history erasing the data of the previous simulation */
  def resetHistory(
      environment: Environment = Environment.BasicEnvironment,
      population: Population = PopulationFactory(50)): Unit =
    history = initializeWorld(environment, population)

  private def initializeWorld(
      startingEnvironment: Environment,
      startingPopulation: Population): History =
    Seq(WorldSnapshot(1, startingEnvironment, startingPopulation))

  /** @return the last snapshot of the current simulation */
  def getLastSnapshot(): WorldSnapshot = history.head

  /** @return the living population of the last snapshot */
  def getLastLivingPopulation(): Population = getLastSnapshot().livingPopulation()

  /** Generates the snapshot at the start of the new iteration, evolving the environment,
   * preparing the ditasters to apply and closing the previous snapshot  */
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

  /** Applies all the disasters to the population */
  def applyDisturbances(): Unit =
    for dino <- getLastLivingPopulation()
    yield Environment.applyEnvironmentDamage(dino, getLastSnapshot().environment)
    getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation().toList))

  /** Damage the population if there's not enough food for all the dinosaurs */
  private def dinosaursEatingPhase(): Unit =
    val difference =
      getLastSnapshot().environment.vegetationAvailable / getLastLivingPopulation().size
    if (difference < 1)
      getLastLivingPopulation()
        .sorted(_.age - _.age)
        .take(((1 - difference) * getLastLivingPopulation().size).toInt)
        .foreach(_.kill())

  /** Makes the remaining living dinosaurs eat, move in the world and reproduce*/
  def dinosaursPhase(): Unit =
    dinosaursEatingPhase()
    getLastSnapshot().livingPopulation().foreach(_.moveDinosaur())
    getLastSnapshot().population = PopulationFactory.reproduction(getLastSnapshot().livingPopulation())

  /** @return true if the simulation has reached any of its ending conditions */
  def isSimulationOver(): Boolean =
    history.size >= EngineConstants.max_iterations ||
      getLastLivingPopulation().size >= EngineConstants.max_population_size || getLastLivingPopulation().size <= 0

