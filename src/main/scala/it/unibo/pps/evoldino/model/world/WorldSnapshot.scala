package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.model.dinosaur.{ Dinosaur, ImmutableDinosaur, Population }
import it.unibo.pps.evoldino.model.disaster.Disaster

import scala.util.Random

trait WorldSnapshot {

  def environment: Environment

  var population: Population

  val disasters: Seq[Disaster]

  def livingPopulation(): Population = population filter (_.isAlive)

  def closeSnapShot() =
    population = population map (ImmutableDinosaur(_))
}

object WorldSnapshot {

  def apply(
      environment: Environment,
      population: Population,
      disasters: Seq[Disaster] = Seq.empty): WorldSnapshot =
    new WorldSnapshotImpl(environment, population, disasters)

  private class WorldSnapshotImpl(
      override val environment: Environment,
      var population: Population,
      override val disasters: Seq[Disaster])
      extends WorldSnapshot
}
