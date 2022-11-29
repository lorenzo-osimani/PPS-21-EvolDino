package it.unibo.pps.evoldino.model.world

object WorldConstants {
  // Environment Constants
  val min_temperature: Float = -50
  val max_temperature: Float = 70

  val min_humidity: Float = -50
  val max_humidity: Float = 70

  val max_vegetation_percentage: Float = 10000

  val characteristicEvolutionProbability = 0.3

  val environmentEvolutionDelta: Float = 1

  // World Dimensions
  val dim_h_world = 100
  val dim_w_world = 100

}
