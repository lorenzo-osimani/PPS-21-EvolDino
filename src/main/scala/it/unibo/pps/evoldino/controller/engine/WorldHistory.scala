package it.unibo.pps.evoldino.controller.engine

import WorldSnapshot.Population
import EngineConstants.*
import it.unibo.pps.evoldino.model.Environment

object WorldHistory {

  type History = Seq[WorldSnapshot]

  var history: History = initializeWorld()

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

  def applyDisturbances(): Unit = {}

  def nextIteration(): Unit =
    history = WorldSnapshot(environmentEvolutionPhase(), reproductionPhase()) +: history

  private def reproductionPhase(): Population = getLastSnapshot().population

  private def environmentEvolutionPhase(): Environment =
    Environment.evolveFromEnvironment(getLastSnapshot().environment)
}
