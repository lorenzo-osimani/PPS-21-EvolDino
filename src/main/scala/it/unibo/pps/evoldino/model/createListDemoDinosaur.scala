package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.model.dinosaur.*
import it.unibo.pps.evoldino.model.dinosaur.gene.*

import scala.collection.mutable.ListBuffer

def createListDemoDinosaur(): Population =

  val dino1: Dinosaur =
    Dinosaur(Gene(23, 1234, 100, "green"), Male, (100, 100))

  val dino2: Dinosaur =
    Dinosaur(Gene(23, 1234, 100, "green"), Male, (19, 71))

  val dino3: Dinosaur =
    Dinosaur(Gene(23, 1234, 100, "green"), Male, (35, 75))

  val dino4: Dinosaur =
    Dinosaur(Gene(23, 1234, 100, "green"), Male, (20, 60))

  val dino5: Dinosaur =
    Dinosaur(Gene(23, 1234, 100, "green"), Male, (30, 70))

  val dino6: Dinosaur =
    Dinosaur(Gene(23, 1234, 100, "green"), Male, (50, 50))
  
  val populationTest = new ListBuffer[Dinosaur]()
  populationTest += dino1
  populationTest += dino2
  populationTest += dino3
  populationTest += dino4
  populationTest += dino5
  populationTest += dino6

  val pTest = populationTest.toList

  pTest
