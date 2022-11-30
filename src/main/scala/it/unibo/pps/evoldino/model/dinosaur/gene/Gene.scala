package it.unibo.pps.evoldino.model.dinosaur.gene

import it.unibo.pps.evoldino.model.dinosaur.*
import it.unibo.pps.evoldino.model.dinosaur.gene.*
import it.unibo.pps.evoldino.utils.GlobalUtils.chooseBetweenTwo

import scala.util.Random

sealed trait Gene:
  val idealTemperature: Int
  val idealHumidity: Int
  val lifespan: Int
  val color: String

  override def toString: String =
    super.toString +
      "\n idealTemperature: " + idealTemperature +
      "\n idealHumidity: " + idealHumidity +
      "\n lifespan: " + lifespan +
      "\n color: " + color

object Gene:

  def apply(idealTemperature: Int, idealHumidity: Int, lifespan: Int, color: String): Gene =
    new GeneImpl(idealTemperature, idealHumidity, lifespan, color)

  def apply(father: Gene, mother: Gene): Gene =
    new GeneImpl(
      combineGenes(father.idealTemperature, mother.idealTemperature),
      combineGenes(father.idealHumidity, mother.idealHumidity),
      combineGenes(father.lifespan, mother.lifespan),
      chooseBetweenTwo(father.color, mother.color)
    )

  def randomizedGene() =
    Gene(
      getRandomGenotype(TemperatureType),
      getRandomGenotype(HumidityType),
      getRandomGenotype(LifeSpan),
      "blue"
    )

  private def combineGenes(first_gene: Int, second_gene: Int): Int =
    val range = (first_gene - second_gene).abs
    val delta = Random.nextInt(range + 1) - (range / 2)
    (first_gene + second_gene) / 2 + delta

  private class GeneImpl(
      override val idealTemperature: Int,
      override val idealHumidity: Int,
      override val lifespan: Int,
      override val color: String)
      extends Gene
