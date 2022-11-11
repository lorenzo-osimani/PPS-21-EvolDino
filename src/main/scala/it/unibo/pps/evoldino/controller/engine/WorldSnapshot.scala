package it.unibo.pps.evoldino.controller.engine

import scala.util.Random
import WorldSnapshot.Population
import it.unibo.pps.evoldino.model.Environment

trait WorldSnapshot {

  def environment: Environment

  var population: Population

  var expanded: Boolean

  def damagePopulation(damage: Float): Unit =
    if (!expanded)
      this.population = Random.shuffle(population).drop((damage * population.size).toInt)
    else throw new IllegalArgumentException

  def closeSnapShot(): Unit = this.expanded = true
}

object WorldSnapshot {

  type Population = Seq[Int]

  def apply(environment: Environment, population: Population): WorldSnapshot =
    new WorldSnapshotImpl(environment, population, false)

  private class WorldSnapshotImpl(
      override val environment: Environment,
      var population: Population,
      var expanded: Boolean)
      extends WorldSnapshot
}
