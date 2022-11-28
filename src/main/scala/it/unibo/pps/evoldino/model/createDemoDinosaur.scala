package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.model.dinosaur.*

def createDemoDinosaur(): List[Dinosaur] =

  val dino1: Dinosaur =
    Dinosaur("erbivorous", "erbo1", 23, 1234, "green", Male, 100, (100, 100))

  val dino2: Dinosaur =
    Dinosaur("erbivorous", "erbo2", 23, 1234, "green", Male, 100, (19, 71))

  val dino3: Dinosaur =
    Dinosaur("erbivorous", "erb3", 23, 1234, "green", Male, 100, (35, 75))

  val dino4: Dinosaur =
    Dinosaur("erbivorous", "carn4", 23, 1234, "green", Male, 100, (20, 60))

  val dino5: Dinosaur =
    Dinosaur("erbivorous", "pollo5", 23, 1234, "green", Male, 100, (30, 70))

  val dino6: Dinosaur =
    Dinosaur("erbivorous", "erbo6", 23, 1234, "green", Male, 100, (50, 50))

  // usaare listbuffer perche' mutabile//vedere qui
  // https://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable/
  import scala.collection.mutable.ListBuffer

  val populationTest = new ListBuffer[Dinosaur]()
  populationTest += dino1
  populationTest += dino2
  populationTest += dino3
  populationTest += dino4
  populationTest += dino5
  populationTest += dino6

  val pTest = populationTest.toList

  pTest
