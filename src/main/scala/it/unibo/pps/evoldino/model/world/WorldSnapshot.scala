package it.unibo.pps.evoldino.model.world

import it.unibo.pps.evoldino.model.dinosaur.{ Dinosaur, ImmutableDinosaur, Population }
import it.unibo.pps.evoldino.model.disaster.Disaster

import scala.util.Random

trait WorldSnapshot {

  def number_iteration: Int

  def environment: Environment

  var population: Population

  val disasters: Seq[Disaster]

  def livingPopulation(): Population = population filter (_.isAlive)

  def closeSnapShot() =
    population = population map (ImmutableDinosaur(_))
}

object WorldSnapshot {

  def apply(
      number_iteration: Int,
      environment: Environment,
      population: Population,
      disasters: Seq[Disaster] = Seq.empty): WorldSnapshot =
    new WorldSnapshotImpl(number_iteration, environment, population, disasters)

  private class WorldSnapshotImpl(
      override val number_iteration: Int,
      override val environment: Environment,
      var population: Population,
      override val disasters: Seq[Disaster])
      extends WorldSnapshot
}
