package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.model.dinosaur.{ Dinosaur, ImmutableDinosaur }
import it.unibo.pps.evoldino.model.disaster.Disaster

import scala.util.Random

type Population = Seq[Dinosaur]

trait WorldSnapshot {

  def environment: Environment

  var population: Population

  val disasters: Seq[Disaster]

  var ended: Boolean

  def damagePopulation(percentage: Float, damagePerDino: Int): Unit =
    if (!ended)
      Random
        .shuffle(livingPopulation())
        .take((percentage * population.size).toInt) foreach (_.damageDinosaur(damagePerDino))
    else throw new IllegalArgumentException

  def livingPopulation(): Population = population filter (_.isAlive)

  def closeSnapShot() =
    ended = true
    population = population map (ImmutableDinosaur(_))
}

object WorldSnapshot {

  def apply(
      environment: Environment,
      population: Population,
      disasters: Seq[Disaster] = Seq.empty): WorldSnapshot =
    new WorldSnapshotImpl(environment, population, disasters, false)

  private class WorldSnapshotImpl(
      override val environment: Environment,
      var population: Population,
      override val disasters: Seq[Disaster],
      var ended: Boolean)
      extends WorldSnapshot
}
