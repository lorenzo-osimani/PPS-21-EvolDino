package it.unibo.pps.evoldino.controller.engine

import EngineConstants.*
import EngineController.*
import it.unibo.pps.evoldino.model.dinosaur.{ Population, PopulationFactory, Reproduction }
import it.unibo.pps.evoldino.model.world.{ Environment, WorldSnapshot }
import it.unibo.pps.evoldino.model.disaster.Disaster

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld(Environment.BasicEnvironment, Seq.empty)

  def resetHistory(
      environment: Environment = Environment.BasicEnvironment,
      population: Population = PopulationFactory(6)): Unit =
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
    val vegetation_necessary = (getLastLivingPopulation() map (_.genes.hunger)).sum
    val difference = getLastSnapshot().environment.vegetationAvailable / vegetation_necessary
    if (difference < 1)
      getLastSnapshot().population foreach (dino =>
        dino.damageDinosaur((1 - difference) * dino.genes.hunger * 10)
      )

  def applyDisturbances(): Unit =
    getLastSnapshot().disasters foreach (_.applyDisaster(getLastLivingPopulation().toList))

  def reproductionPhase(): Unit =
    getLastSnapshot().population = Reproduction.reproduction(getLastSnapshot().livingPopulation())

  def isSimulationOver(): Boolean =
    history.size >= max_iterations ||
      getLastLivingPopulation().size >= max_population_size // || getLastLivingPopulation().size <= 0
}
