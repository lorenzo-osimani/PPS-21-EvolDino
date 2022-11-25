package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.dinosaur.GeneClassification
import scala.collection.mutable.ListBuffer
import scala.util.Random

  case object Reproduction {

    private def generateDinosaur(l: List[Dinosaur]): List[Dinosaur] = {
      val newGeneration: Int = Random.between(100,1000)
      val newListDino = new ListBuffer[Dinosaur]
      newListDino += newGeneration
      print(newListDino.toList)

  }

}
