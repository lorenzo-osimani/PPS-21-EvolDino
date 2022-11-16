package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.model
import it.unibo.pps.evoldino.model.Disaster
import it.unibo.pps.evoldino.model.Disaster.{Drought, Earthquake, Meteorite}
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.createDemoListRandomDisasterProb

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
import scala.util.Random

def createList(n: Int) : List[Disaster] =

  //val myDis = createDemoListRandomDisasterProb()
  val dL = new ListBuffer[Disaster]
  for (i <- 1 to n)
    dL += createDemoListRandomDisasterProb()
  val dTestRandomProb = dL.toList

  return dTestRandomProb

  /*  val
    case class Earthquake(x: Int, y: Int) extends Disaster with AreaEffect :
      override val name = "Earthquake"
      override val damage = 1000
      override val extension = 3
      override val probability = 0.5
      override val coordinates: (Int, Int) = (x, y)
  */
  /*
  var dino1: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erbo1"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int,Int) = (50,50)
    val dinoID: Int = 1

  var dino2: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erbo2"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 2

  var dino3: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erb3"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 2

  var dino4: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "carn4"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 3

  var dino5: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "pollo5"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 5
    
  var dino6: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erbo6"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 6

  //usaare listbuffer perche' mutabile//vedere qui
  // https://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable/
  import scala.collection.mutable.ListBuffer

  var populationTest = new ListBuffer[Dinosaur]()
  populationTest += dino1
  populationTest += dino2
  populationTest += dino3
  populationTest += dino4
  populationTest += dino5
  populationTest += dino6

  val pTest = populationTest.toList
  
  return pTest
  */


/*if(disWithProb == Disaster.Earthquake)
  disWithProb.AreaEffect.apply(generateRandomExtensionPList(),(0,0))
  //disWithProb.Earthquake.
  print("d")
*/
/*
if(disWithProb.extension != None)
disWithProb.extension = generateRandomExtensionPList()
if(isypeOf(disWithProb) == Disaster.Drought
print("sss")
disWithProb(e = generateRandomExtensionPList())
*/

