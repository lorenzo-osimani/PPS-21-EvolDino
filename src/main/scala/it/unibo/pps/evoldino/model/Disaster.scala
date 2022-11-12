package it.unibo.pps.evoldino.model

import scalafx.geometry.Point2D
import scalafx.geometry.Point2D.Zero.x
import scalafx.geometry.Point2D.Zero.y

/** Represent a Disaster */
sealed trait Disaster:

  def name: String
  def damage: Int
  def probability: Double

  override def toString: String = {
    super.toString +
      "\n name: " + name
    "\n damage: " + damage
    //if
    //if(extension !== null) => "\n extension " + extension +
    //"\n coordinateX" + coordinates(x)
    //"\n coordinateY" + coordinates(y)
  }
//cè un modo di creare un array per riprendere i danni creati
//problema che aclune variabili devono stare entro un certo range
//è posbbile settare questo range da pruma nella definizione delle def

/*
  def DoARandomDisaster: () => Disaster = match
    case IceAge
    case EarthQuake,
    case _ => throw Error
 */

sealed trait AreaEffect:
  def extension: Int
  def coordinates: (Int, Int)

sealed trait ClimateEffect:
  def temperature: Int

object Disaster {

  case class Earthquake(x: Int, y: Int) extends Disaster with AreaEffect:
    override val name = "Earthquake"
    override val damage = 1000
    override val extension = 3
    override val probability = 0.5
    override val coordinates: (Int, Int) = (x, y)

  case class Meteorite(x: Int, y: Int) extends Disaster with AreaEffect:
    override val name: String = "Meteorite"
    override val damage: Int = 1000
    override val extension = 3
    override val probability: Double = 0.5
    override val coordinates: (Int, Int) = (x, y)

  case object IceAge extends Disaster with ClimateEffect:
    override val name: String = "IceAge"
    override val damage: Int = 1000
    override val probability: Double = 0.5
    override val temperature: Int = 100000

  case object Drought extends Disaster with ClimateEffect:
    override val name: String = "Drought"
    override val damage: Int = 100
    override val probability: Double = 0.3
    override val temperature: Int = 10000
}
