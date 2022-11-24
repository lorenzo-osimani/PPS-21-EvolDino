package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.dinosaur.GeneClassification
import it.unibo.pps.evoldino.model.world.WorldSnapshot
import scala.util.Random

  case object Reproduction {

    private def generateDinosaur(d: List[Dinosaur]): List[Dinosaur] = {
      val newPopulation: Int = GeneClassification.REPRODUCTION_GENE
      Random.between(1, 5)
      reproduce(female[0], male[0])
  }

}
