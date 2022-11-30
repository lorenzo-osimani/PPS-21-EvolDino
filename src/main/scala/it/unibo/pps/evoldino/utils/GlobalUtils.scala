package it.unibo.pps.evoldino.utils

import scala.util.Random

object GlobalUtils {
  private val random = new scala.util.Random

  def calculateProbability(threshold: Double): Boolean = random.nextDouble() <= threshold

  def chooseBetweenTwo[A](first_gene: A, second_gene: A): A =
    if (Random.nextBoolean()) first_gene else second_gene
}
