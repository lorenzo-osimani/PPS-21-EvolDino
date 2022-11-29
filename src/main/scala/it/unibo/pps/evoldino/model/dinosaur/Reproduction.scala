package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.dinosaur.Population
import it.unibo.pps.evoldino.model.dinosaur.gene.Gene

import scala.util.Random

object Reproduction {

  def reproduction(population: Population): Population =
    val progeny: Population = Seq.empty
    val couples =
      (population filter (_.gender == Male)) zip (population filter (_.gender == Female))
    for (father, mother) <- couples
    yield Dinosaur(
      Gene(father.genes, mother.genes),
      Male,
      mother = Option(mother),
      father = Option(father)
    ) +: progeny
    progeny ++: (population filter (_.isAlive))
}
