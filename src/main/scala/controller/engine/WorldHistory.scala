package controller.engine

import controller.engine.WorldSnapshot.Population
import model.Environment

object WorldHistory {

  type History =  Seq[WorldSnapshot]

  var history: History = initializeWorld()

  def initializeWorld(parameters: String = "hello"): History = {
    Seq(WorldSnapshot(Environment.BasicEnvironment, Seq.empty))
  }

  def getLastSnapshot(): WorldSnapshot = history.head

  def dinosaursEatingPhase(): Unit = {}

  def applyDisturbances(): Unit = {}

  def nextIteration(): Unit =
    history = WorldSnapshot(environmentEvolutionPhase(), reproductionPhase()) +: history

  private def reproductionPhase(): Population = getLastSnapshot().population

  private def environmentEvolutionPhase(): Environment = {
    Environment(getLastSnapshot().environment)
  }
}
