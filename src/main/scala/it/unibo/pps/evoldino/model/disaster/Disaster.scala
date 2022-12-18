package it.unibo.pps.evoldino.model.disaster

import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.disaster.{ AreaEffect, ClimateEffect, Disaster }
import it.unibo.pps.evoldino.model.world.Environment
import it.unibo.pps.evoldino.model.world.WorldConstants.*
import it.unibo.pps.evoldino.model.world.WorldHistory.getLastLivingPopulation
import it.unibo.pps.evoldino.model.dinosaur.Population

import scala.collection.mutable.ListBuffer
import scala.util.Random

/** Represent a Disaster */
sealed trait Disaster:

  def name: String

  def applyDisaster(p: Population): Population =
    print("\n nessun disastro applicato \n")
    p

  override def toString: String =
    super.toString +
      "\n name: " + name

abstract class AreaEffect extends Disaster:
  val extension: Int
  val coordinates: (Int, Int)
  def damage: Int

  override def applyDisaster(p: Population): Population =
    p.filter(dino =>
      dino.coordinates._1 >= coordinates._1 - extension &&
        dino.coordinates._1 <= coordinates._1 + extension &&
        dino.coordinates._2 >= coordinates._2 - extension &&
        dino.coordinates._2 <= coordinates._2 + extension
    ).foreach(_.damageDinosaur(damage))
    p

  override def toString: String =
    super.toString +
      "\n damage: " + damage +
      "\n extension " + extension +
      "\n coordinateX " + coordinates._1 +
      "\n coordinateY " + coordinates._2 + "\n"

abstract class ClimateEffect extends Disaster:
  val environment: Environment

  override def toString: String =
    super.toString +
      environment.toString

  override def applyDisaster(p: Population): Population =
    for dino <- p yield Environment.applyEnvironmentDamage(dino, environment)
    p

object Disaster {

  case class Earthquake(
      e: Int = Random.between(min_range_earthquake, max_range_earthquake),
      c: (Int, Int) = (Random.nextInt(dim_w_world + 1), Random.nextInt(dim_h_world + 1)))
      extends AreaEffect:
    override val name: String = DisasterType.EARTHQUAKE.name
    override val damage: Int = 25
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case class Meteorite(
      e: Int = Random.between(min_range_meteorite, max_range_meteorite),
      c: (Int, Int) = (Random.nextInt(dim_w_world + 1), Random.nextInt(dim_h_world + 1)))
      extends AreaEffect:
    override val name: String = DisasterType.METEORITE.name
    override val damage: Int = 40
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case object IceAge extends ClimateEffect:
    override val name: String = DisasterType.ICEAGE.name
    override val environment: Environment = Environment.IceAgeEnvironment

  case object Drought extends ClimateEffect:
    override val name: String = DisasterType.DROUGHT.name
    override val environment: Environment = Environment.DroughtEnvironment

}

enum DisasterType(val name: String, val probability: Int) {
  case EARTHQUAKE extends DisasterType("Earthquake", 5)
  case METEORITE extends DisasterType("Meteorite",3)
  case ICEAGE extends DisasterType("Ice Age",1)
  case DROUGHT extends DisasterType("Drought",7)
}
