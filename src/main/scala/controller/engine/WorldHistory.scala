package controller.engine

import model.Environment

object WorldHistory {

  type History =  Seq[WorldSnapshot]

  var history: History = initializeWorld()

  def initializeWorld(parameters: String = "hello"): History = {
    Seq(WorldSnapshot(Environment.BasicEnvironment, Seq.empty))
  }

  def nextIteration(): Unit = {

  }
}
