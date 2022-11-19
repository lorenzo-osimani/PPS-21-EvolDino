package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.dinosaur.GeneClassification
import it.unibo.pps.evoldino.model.world.WorldSnapshot
import scala.util.Random

object Reproduction {
  override def newPopulation: Int = {
    val Population: Int = GeneClassification.REPRODUCTION_GENE
    Random.shuffle(Population)
  }
