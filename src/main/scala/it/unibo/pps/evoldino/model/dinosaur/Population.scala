package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.dinosaur.gene.Gene
import it.unibo.pps.evoldino.utils.GlobalUtils.chooseBetweenTwo

import scala.collection.mutable.ListBuffer

type Population = Seq[Dinosaur]

object PopulationFactory:

  def apply(size: Int): Population =
    Seq.fill(size)(Dinosaur.randomizedDinosaur())

  /**Apply the reproduction on a given population*/
  def reproduction(population: Population): Population =
    val progeny: ListBuffer[Dinosaur] = ListBuffer.empty
    val couples =
      (population filter (_.gender == Male)) zip (population filter (_.gender == Female))
    for (father, mother) <- couples
      yield progeny += Dinosaur(
        Gene(father.genes, mother.genes),
        chooseBetweenTwo(Male, Female),
        mother = Option(mother),
        father = Option(father)
      )
    (population filter (_.isAlive)) ++: progeny.toSeq


