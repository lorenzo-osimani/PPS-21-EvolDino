package it.unibo.pps.evoldino.model.dinosaur

import scala.util.Random

sealed trait Gene:
  val height: Int
  val weight: Int
  val lifespan: Int
  val color: String

  override def toString: String =
    super.toString +
      "\n height: " + height +
      "\n weight: " + weight +
      "\n lifespan: " + lifespan +
      "\n color: " + color

object Gene:

  def apply(height: Int, weight: Int, lifespan: Int, color: String): Gene =
    new GeneImpl(height, weight, lifespan, color)

  def apply(father: Gene, mother: Gene): Gene =
    new GeneImpl(
      combineGenes(father.height, mother.height),
      combineGenes(father.weight, mother.weight),
      combineGenes(father.lifespan, mother.lifespan),
      "color")

  private def combineGenes(first_gene: Int, second_gene: Int): Int =
    val range = (first_gene - second_gene)/2
    val delta = Random.between(-range, range+1)
    (first_gene + second_gene)/2 + delta

  private class GeneImpl(override val height: Int,
                         override val weight: Int,
                         override val lifespan: Int,
                         override val color: String) extends Gene


