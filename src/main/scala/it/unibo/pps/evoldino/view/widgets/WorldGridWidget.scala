package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.model.world.WorldConstants
import it.unibo.pps.evoldino.model.disaster.DisasterType
import it.unibo.pps.evoldino.view.components.GenericIcon
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Tooltip}
import scalafx.scene.layout.{Background, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, StrokeType}

object WorldGridWidget:

  private val base_color = Color.rgb(70,70,70)

  private def disasterContains(x: Int, y: Int, origin_x: Int, origin_y: Int, coverage: Int): Boolean =
    origin_x - coverage <= x && origin_x + coverage >= x &&
      origin_y - coverage <= y && origin_y + coverage >= y

  def renderWorld(population: Map[(Int, Int), String], disasters: Seq[((Int, Int), Int, String)] = Seq.empty) =
    for {
      x <- 0 until WorldConstants.dim_w_world
      y <- 0 until WorldConstants.dim_h_world
    } yield
      var tooltipMessage: String = ""
      var color: Color = base_color
      if (population.contains((x, y)))
        color = Color.valueOf(population.get((x, y)).getOrElse("green"))
        tooltipMessage = "Dinosaur"
        Tooltip.install(worldGrid(x)(y), Tooltip(tooltipMessage))
      else
        disasters foreach (dis =>
          if (disasterContains(x, y, dis._1._1, dis._1._2, dis._2)) {
            dis._3 match
              case DisasterType.EARTHQUAKE.name =>
                color = Color.Brown
                tooltipMessage = "Earthquake"
              case DisasterType.METEORITE.name =>
                color = Color.LightCoral
                tooltipMessage = "Meteorite"
            Tooltip.install(worldGrid(x)(y), Tooltip(tooltipMessage))
          })
      worldGrid(x)(y).fill = color

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
      fill = base_color
    worldGridWidget.add(cell, x, y)
    worldGrid(x)(y) = cell
  }
