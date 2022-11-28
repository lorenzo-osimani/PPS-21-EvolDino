package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.world.Population

import scala.util.Random

object Reproduction {

  def reproduction(population: Population): Population =
    val progeny: Population = Seq.empty
    val couples = (population filter (_.gender == Male)) zip (population filter (_.gender == Female))
    for
      (father, mother) <- couples
    yield Dinosaur("hello", Gene(father.genes, mother.genes), Male, (0,0), Option(mother), Option(father)) +: progeny
    progeny ++: (population filter (_.isAlive))
}
