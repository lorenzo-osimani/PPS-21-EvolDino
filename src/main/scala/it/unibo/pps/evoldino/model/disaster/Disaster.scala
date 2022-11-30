package it.unibo.pps.evoldino.model.disaster

import cats.implicits.catsSyntaxMonadIdOps
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.disaster.{AreaEffect, ClimateEffect, Disaster}

import scala.collection.mutable.ListBuffer
import scala.math.Fractional.Implicits.infixFractionalOps
import scala.math.Integral.Implicits.infixIntegralOps
import scala.math.Numeric.Implicits.infixNumericOps
import scala.math.Ordering.Implicits.infixOrderingOps

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
    // val dinoListNew = new ListBuffer[Dinosaur]
    for (dino <- p)
      if ((coordinates._1 <= dino.coordinates._1 && dino.coordinates._1 <= coordinates._1 + extension)
        && (coordinates._2 <= dino.coordinates._2 && dino.coordinates._2 <= coordinates._2 + extension))
        dino.damageDinosaur(damage)
    // dinoListNew += dino
    // dinoListNew.toList
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

  case class Earthquake(e: Int, c: (Int, Int)) extends AreaEffect:
    override val name = "Earthquake"
    override val damage = 20
    override val probability = 5
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case class Meteorite(e: Int, c: (Int, Int)) extends AreaEffect:
    override val name: String = "Meteorite"
    override val damage: Int = 40
    override val probability: Int = 3
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case object IceAge extends ClimateEffect:
    override val name: String = "IceAge"
    override val damage: Int = 30
    override val probability: Int = 1
    override val temperature: Int = 100

  case object Drought extends ClimateEffect:
    override val name: String = "Drought"
    override val damage: Int = 10
    override val probability: Int = 7
    override val temperature: Int = 5000
}
