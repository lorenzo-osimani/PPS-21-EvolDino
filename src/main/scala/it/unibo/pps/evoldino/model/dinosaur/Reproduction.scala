package it.unibo.pps.evoldino.model.dinosaur

<<<<<<< HEAD
import it.unibo.pps.evoldino.model.dinosaur.Population
import it.unibo.pps.evoldino.model.dinosaur.gene.Gene
import it.unibo.pps.evoldino.utils.GlobalUtils.chooseBetweenTwo

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Reproduction {

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
=======
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

>>>>>>> ecf226acd3d2f1a2d984da4a65d478c0245809cb
}
