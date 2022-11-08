package it.unibo.pps.evoldino

object GlobalUtils {
  private val random = new scala.util.Random
  def calculateProbability(threshold: Double): Boolean = random.nextDouble() <= threshold
}
