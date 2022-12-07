package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.model.world.WorldConstants
import it.unibo.pps.evoldino.view.components.Icon
import it.unibo.pps.evoldino.view.widgets.WorldGridWidget.worldGridWidget
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Group
import scalafx.scene.layout.{Background, GridPane, TilePane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}

object WorldGridWidget:

  def renderWorld(population: Seq[(Int, Int)], disasters: Seq[((Int, Int), Int)] = Seq.empty) =
    val disasters_coverage: Seq[(Int, Int)] = for {
      dis <- disasters
      origin_x = dis._1._1
      origin_y = dis._1._2
      delta_x <- -dis._2 until dis._2
      delta_y <- -dis._2 until dis._2
    } yield
      (origin_x + delta_x, origin_y + delta_y)


    for {
      x <- 0 until WorldConstants.dim_w_world
      y <- 0 until WorldConstants.dim_h_world
    } yield
      if (population.contains((x, y)))
        worldGrid(x)(y).fill = Color.Green
      else if (disasters_coverage.contains(x, y))
        worldGrid(x)(y).fill = Color.DarkRed
      else
        worldGrid(x)(y).fill = Color.Grey

  val worldGridWidget: GridPane = new GridPane:
    margin = Insets(5)

  val worldGrid: Array[Array[Rectangle]] =
    Array.fill(WorldConstants.dim_w_world)(Array.fill(WorldConstants.dim_h_world)(null))

  for {
    x <- 0 until WorldConstants.dim_w_world
    y <- 0 until WorldConstants.dim_h_world
  } yield {
    val cell = new Rectangle:
      width = 5
      height = 5
      fill = Color.Grey
    worldGridWidget.add(cell, x, y)
    worldGrid(x)(y) = cell
  }
