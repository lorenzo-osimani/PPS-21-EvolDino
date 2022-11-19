package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.model
import it.unibo.pps.evoldino.model.Disaster.{ Drought, Earthquake, IceAge, Meteorite }
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.AreaEffect
import it.unibo.pps.evoldino.model.ClimateEffect

import scala.collection.mutable.ListBuffer
import scala.util.Random

def appDino(dino: Dinosaur, dam : Disaster): Dinosaur =

    /*
    override def testAge: Int = testDinoID

    override def testLifePoints: Int = (testLifePoints - dam.damage)

    override def testCoordinates: (Int, Int) = testCoordinates

    override def testDinoID: Int = testDinoID
    */
  return dino

def applyDisaster(d: List[Disaster], p: List[Dinosaur]): List[Dinosaur] =

  val dinoListNew = new ListBuffer[Dinosaur]
  //ListBuffer.empty ++= l
  /*
  def p2= new ListBuffer[Dinosaur]
  def p3 = new ListBuffer[Dinosaur]
  //l.to[ListBuffer]
  //p2 = p.to[ListBuffer]
  p2.empty ++= p
  */
  /*

  def applyEarthquake(earthquake: Earthquake, p: List[Dinosaur]) : List[Dinosaur] =
      for (dinosaur <- p)
        dinosaur.testCoordinates == (earthquake.coordinates._1,earthquake.coordinates._2)
        "dinosaur found"
   */

  for (disaster <- d)
    print("\n EVALUATING: " + disaster.name + "\n")
    disaster match {
      case _: AreaEffect =>
        if (disaster == Earthquake)
          println("terremoto")
          for (dinosaur <- p)
            if (true)
              print("colpito a" + dinosaur.testCoordinates + "\n")
              appDino(dinosaur,disaster)
              dinoListNew += appDino(dinosaur,disaster)
            else
              dinoListNew += dinosaur
        if (disaster == Meteorite)
          println("meteorite")
          for (dinosaur <- p)
            print("eval coordi")
            print(dinosaur.testCoordinates)
            /*print(disaster.coordinates)
            if (dinosaur.testCoordinates._1.equals(disaster.coordinates._1))
              print("colpito a " + dinosaur.testCoordinates + "\n")
              appDino(dinosaur, disaster)
              dinoListNew += appDino(dinosaur, disaster)
            else
              dinoListNew += dinosaur*/
      case _: ClimateEffect =>
        if (disaster == Drought)
          println("colpito siccita" + "\n")
          for (dinosaur <- p)
            appDino(dinosaur, disaster)
            dinoListNew += appDino(dinosaur, disaster)
        if (disaster == IceAge)
          println("colpito glaciazione" + "\n")
          for (dinosaur <- p)
            appDino(dinosaur,disaster)
            dinoListNew += appDino(dinosaur, disaster)
      case null => println("ERROR")
  }

  dinoListNew.toList
