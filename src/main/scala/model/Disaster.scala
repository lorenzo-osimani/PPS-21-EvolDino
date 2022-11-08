package model

import scalafx.geometry.Point2D
import scalafx.geometry.Point2D.Zero.x
import scalafx.geometry.Point2D.Zero.y
/** Represent a Disaster */
sealed trait Disaster:

  def name: String
  def damage: Int
  def probability: Double
  def extension: Int

  //nonnn sarebbe meglio che l engine decidesse le coordinate
  //override coordinates: engine probabilmente

  def temperature: Int
  def coordinates: (Int, Int)

  case object Earthquake extends Disaster :
    override val name: String = "Earthquake"
    override val damage: Int = 1000
    override val extension: Int = 3
    override val probability: Double = 0.5
    override val temperature: Int =  0
    override val coordinates: (Int, Int) = (50,50)

  case object Meteorite extends Disaster :
    override val name: String = "Meteorite"
    override val damage: Int = 1000
    override val extension: Int = 3
    override val probability: Double = 0.5
    override val temperature: Int =  0
    override val coordinates: (Int, Int) = (50,50)

  //refactoring
  case object IceAge extends Disaster :
    override val name: String = "IceAge"
    override val damage: Int = 1000
    override val probability: Double = 0.5
    override val extension: Int =  0
    override val temperature: Int = 100000
    override val coordinates: (Int, Int) = (0,0)
  //override val coordinates: Point2D = (Nil, Nil)

  case object Drought extends Disaster :
    override val name: String = "Drought"
    override val damage: Int = 100
    override val probability: Double = 0.3
    override val extension: Int =  0
    override val temperature: Int = 10000
    override val coordinates: (Int, Int) = (0,0)

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
