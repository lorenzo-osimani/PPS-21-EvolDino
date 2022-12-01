package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.Disaster
import scala.collection.mutable.ListBuffer
import cats.implicits.catsSyntaxMonadIdOps
import math.Ordering.Implicits.infixOrderingOps
import math.Numeric.Implicits.infixNumericOps
import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps


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

  def applyDisasterNEW(p: List[Dinosaur]): List[Dinosaur] =
    print("\n nessun disastro applicato \n")
    p

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
abstract class AreaEffect extends Disaster:
  val extension: Int
  val coordinates: (Int, Int)

  /*FUNZIONANTE
  override def applyDisasterNEW(d: Disaster, p: List[Dinosaur]): List[Dinosaur] =
    //val dinoListNew = new ListBuffer[Dinosaur]
    print("valutazione dinosauri")
    for (dino <- p)
      //ma prende quello che gli viene passato o quello su cui si applicat??
      if(coordinates.equals(dino.testCoordinates))
        print(dino)
        print(" !!COLPITO!! \n")
        dino.testLifePoints = dino.testLifePoints - d.damage
        //dinoListNew += dino
      else
        print(dino)
    print("\n AreaEffect disastro applicato \n")
    //dinoListNew.toList
    p
   */
  override def applyDisasterNEW(p: List[Dinosaur]): List[Dinosaur] =
    //val dinoListNew = new ListBuffer[Dinosaur]
    print("valutazione dinosauri \n")

    p.zipWithIndex.foreach {
      case (dino, count) =>
        if((coordinates._1 <= dino.testCoordinates._1 && dino.testCoordinates._1 <= coordinates._1 + extension)
        && (coordinates._2 <= dino.testCoordinates._2 && dino.testCoordinates._2 <= coordinates._2 + extension))
          dino.testLifePoints = dino.testLifePoints - damage
          print(" !!COLPITO!! \n")
    }

    //dinoListNew += dino
    print("AreaEffect disastro applicato \n")
    //dinoListNew.toList
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

  override def applyDisasterNEW(p: List[Dinosaur]): List[Dinosaur] =
    //val dinoListNew = new ListBuffer[Dinosaur]
    print("applicaziona a tutti i dinosauri \n")
    for (dino <- p)
      dino.testLifePoints = dino.testLifePoints - damage
    //dinoListNew += dino
    print("ClimateEffect disastro applicato \n")
    //dinoListNew.toList
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
