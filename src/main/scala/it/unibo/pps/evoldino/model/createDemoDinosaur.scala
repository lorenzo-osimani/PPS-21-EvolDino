package it.unibo.pps.evoldino.model
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.Disaster

def createDemoDinosaur(): List[Dinosaur] =
  
  sealed trait DinosaurTest:
    def testAge: Int
    def testLifePoints: Int
    def testCoordinates: (Int,Int)
    def testDinoID: Int

    override def toString: String =
      super.toString +
        "\n testAge " + testAge +
        "\n testLifePoints " + testLifePoints +
        "\n testcoordinateX " + testCoordinates._1 +
        "\n testcoordinateY " + testCoordinates._2 +
        "\n testDinoID " + testDinoID +
        "\n OK :) \n"

  val dino1: Dinosaur = new Dinosaur with DinosaurTest :
    val kind: String = "erbivorous"
    val name: String = "erbo1"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val testDinoID: Int = 1

  val dino2: Dinosaur = new Dinosaur with DinosaurTest :
    val kind: String = "erbivorous"
    val name: String = "erbo2"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val testDinoID: Int = 2

  val dino3: Dinosaur = new Dinosaur with DinosaurTest :
    val kind: String = "erbivorous"
    val name: String = "erb3"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val testDinoID: Int = 2

  val dino4: Dinosaur = new Dinosaur with DinosaurTest :
    val kind: String = "erbivorous"
    val name: String = "carn4"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val testDinoID: Int = 3

  val dino5: Dinosaur = new Dinosaur with DinosaurTest :
    val kind: String = "erbivorous"
    val name: String = "pollo5"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val testDinoID: Int = 5

  val dino6: Dinosaur = new Dinosaur with DinosaurTest :
    val kind: String = "erbivorous"
    val name: String = "erbo6"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val testDinoID: Int = 6

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

