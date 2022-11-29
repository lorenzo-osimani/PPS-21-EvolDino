package it.unibo.pps.evoldino.controller.engine

object EngineConstants {

  val ITERATION_MS_1X = 1000
  val ITERATION_MS_2X = ITERATION_MS_1X / 2
  val ITERATION_MS_4X = ITERATION_MS_1X / 4

  // Eating Phase
  val dino_veg_ratio = 2

  // Ending Conditions
  val max_iterations = 1000
  val max_population_size = 1000

  // Parameters
  val min_value_climate = -50
  val max_value_climate = 70

  val dim_h_world = 100
  val dim_w_world = 100

}
