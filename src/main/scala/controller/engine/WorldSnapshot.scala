package controller.engine

import controller.engine.WorldSnapshot.Population
import model.Environment

trait WorldSnapshot {

  def environment: Environment

  def population: Population
  
}

object WorldSnapshot {

  type Population = Seq[Int]

  def apply(environment: Environment, population: Population): WorldSnapshot =
    new WorldSnapshotImpl(environment, population)

  private class WorldSnapshotImpl(
                                override val environment: Environment,
                                override val population: Population
                              ) extends WorldSnapshot
}
