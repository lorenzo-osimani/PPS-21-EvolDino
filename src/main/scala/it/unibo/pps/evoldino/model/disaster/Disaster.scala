package it.unibo.pps.evoldino.model.disaster

import cats.implicits.catsSyntaxMonadIdOps
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.disaster.{ AreaEffect, ClimateEffect, Disaster }
import it.unibo.pps.evoldino.model.world.WorldConstants.*

import scala.collection.mutable.ListBuffer
import scala.util.Random

/** Represent a Disaster */
sealed trait Disaster:

  def name: String
  def damage: Int
  def probability: Int

  def applyDisaster(p: List[Dinosaur]): List[Dinosaur] =
    print("\n nessun disastro applicato \n")
    p

  override def toString: String =
    super.toString +
      "\n name: " + name +
      "\n damage: " + damage +
      "\n probability: " + probability

/* DECIDERE COSA ESTENDERE E CHE FARE */
abstract class AreaEffect extends Disaster:
  val extension: Int
  val coordinates: (Int, Int)

  override def applyDisaster(p: List[Dinosaur]): List[Dinosaur] =
   p.filter(dino =>
      (coordinates._1 <= dino.coordinates._1 && dino.coordinates._1 <= coordinates._1 + extension)
        && (coordinates._2 <= dino.coordinates._2 && dino.coordinates._2 <= coordinates._2 + extension)
      ).foreach(_.damageDinosaur(damage))
   p

  override def toString: String =
    super.toString +
      "\n extension " + extension +
      "\n coordinateX " + coordinates._1 +
      "\n coordinateY " + coordinates._2

abstract class ClimateEffect extends Disaster:
  val temperature: Int

  override def toString: String =
    super.toString +
      "\n temperature " + temperature

  override def applyDisaster(p: List[Dinosaur]): List[Dinosaur] =
    p foreach (_.damageDinosaur(damage))
    p

object Disaster {

  case class Earthquake(
                         e: Int = Random.between(min_range_earthquake, max_range_earthquake),
                         c: (Int, Int) = (Random.nextInt(dim_w_world + 1), Random.nextInt(dim_h_world + 1)))
    extends AreaEffect:
    override val name: String = "Earthquake"
    override val damage: Int = 20
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case class Meteorite(
                        e: Int = Random.between(min_range_meteorite, max_range_meteorite),
                        c: (Int, Int) = (Random.nextInt(dim_w_world + 1), Random.nextInt(dim_h_world + 1)))
    extends AreaEffect:
    override val name: String = "Meteorite"
    override val damage: Int = 40
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case object IceAge extends ClimateEffect:
    override val name: String = "IceAge"
    override val damage: Int = 30
    override val temperature: Int = 100

  case object Drought extends ClimateEffect:
    override val name: String = "Drought"
    override val damage: Int = 10
    override val temperature: Int = 5000



}

enum DisasterType(val probability: Int) extends Disaster {
  case EARTHQUAKE extends DisasterType(5)
  case METEORITE extends DisasterType(3)
  case ICEAGE extends DisasterType(1)
  case DROUGHT extends DisasterType(7)
}