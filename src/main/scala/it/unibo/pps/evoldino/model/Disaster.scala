package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.controller.engine.{ EngineConstants, WorldSnapshot }
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.{ Disaster, Environment }

/*
import scalafx.geometry.Point2D
import scalafx.geometry.Point2D.Zero.x
import scalafx.geometry.Point2D.Zero.y
 */

/** Represent a Disaster */
sealed trait Disaster:

  def name: String
  def damage: Int
  def probability: Int
  def extension: Int
  def coordinates: (Int, Int)
  override def toString: String =
    super.toString +
      "\n name: " + name +
      "\n damage: " + damage +
      "\n probability: " + probability

/*
  def DoARandomDisaster: () => Disaster = match
    case IceAge
    case EarthQuake,
    case _ => throw Error
 */

/* DECIDERE COSA ESTENDERE E CHE FARE */
sealed trait AreaEffect extends Disaster:
  val extension: Int
  val coordinates: (Int, Int)

  override def toString: String =
    super.toString +
      "\n extension " + extension +
      "\n coordinateX " + coordinates._1 +
      "\n coordinateY " + coordinates._2 +
      "\n OK :) \n"

sealed trait ClimateEffect extends Disaster:
  val temperature: Int

  override def toString: String =
    super.toString +
      "\n temperature " + temperature +
      "\n OK"

object Disaster {

  case class Earthquake(e: Int, c: (Int, Int)) extends AreaEffect:
    override val name = "Earthquake"
    override val damage = 1000
    override val probability = 5
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case class Meteorite(e: Int, c: (Int, Int)) extends AreaEffect:
    override val name: String = "Meteorite"
    override val damage: Int = 1000
    override val probability: Int = 3
    override val extension: Int = e
    override val coordinates: (Int, Int) = c

  case object IceAge extends ClimateEffect:
    override val name: String = "IceAge"
    override val damage: Int = 40
    override val probability: Int = 1
    override val temperature: Int = 100
    override val coordinates: (Int, Int) = (0,0)
    override val extension: Int = 0

  case object Drought extends ClimateEffect:
    override val name: String = "Drought"
    override val damage: Int = 30
    override val probability: Int = 7
    override val temperature: Int = 5000
    override val coordinates: (Int, Int) = (0, 0)
    override val extension: Int = 0
}

/*inutile*/
/*def testPop(): Unit=
  val popArrayTest: List[Dinosaur] = createDemoDinosaur()
  println(popArrayTest(0))
  println(popArrayTest(1))
  println(popArrayTest(2))
  println(popArrayTest(3))
  println(popArrayTest(4))
  println(popArrayTest(5))
 */
