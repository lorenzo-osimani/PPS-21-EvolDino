package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.world.WorldSnapshot.Population
import it.unibo.pps.evoldino.model.disaster.Disaster

import scala.util.Random

trait WorldSnapshot {

  def environment: Environment

  var population: Population

  val disasters: Seq[Disaster]

  var ended: Boolean

  def damagePopulation(damage: Float): Unit =
    if (!ended)
      Random.shuffle(livingPopulation()).take((damage * population.size).toInt).foreach(_.kill())
    else throw new IllegalArgumentException

  def livingPopulation(): Population = population filter (_.isAlive)

  def closeSnapShot() =
    ended = true
  // population flatMap() IMMUTABLE DINOSAURS
}

object WorldSnapshot {

  type Population = Seq[Dinosaur]

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
