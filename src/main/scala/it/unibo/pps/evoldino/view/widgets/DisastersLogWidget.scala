package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.disaster.{Disaster, DisasterType}
import it.unibo.pps.evoldino.view.components.{BoldLabel, GenericButton, GenericIcon, SliderPane, ValueLabel}
import it.unibo.pps.evoldino.view.widgets.WorldGridWidget.worldGrid
import scalafx.application.Platform
import scalafx.geometry.*
import scalafx.scene.Node
import scalafx.scene.control.{CheckBox, Label, ScrollPane, Tooltip}
import scalafx.scene.layout.{Background, BackgroundFill, FlowPane, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, FontWeight, TextAlignment}

object DisastersLogWidget:

  def update(disasters: Seq[String]) =
    if (disasters.isEmpty)
      disasterLogWidget.children = Seq(new BoldLabel("Disastri:"), new ValueLabel("Nessun disastro"))
    else
      disasterLogWidget.children = Seq(new BoldLabel("Disastri:"))
      for dis <- disasters
        yield {
          var icon: Node = null
          dis match
            case DisasterType.EARTHQUAKE.name =>
              icon = new Rectangle :
                width = 15
                height = 15
                fill = Color.Brown
            case DisasterType.METEORITE.name =>
              icon = new Rectangle :
                width = 15
                height = 15
                fill = Color.LightCoral
            case DisasterType.ICEAGE.name =>
              icon = GenericIcon("snow.png", "Icona", 15)
            case DisasterType.DROUGHT.name =>
              icon = GenericIcon("sun.png", "Icona", 15)
          Tooltip.install(icon, Tooltip(dis))
          disasterLogWidget.children ++= Seq(icon, new ValueLabel(dis))
        }

  val disasterLogWidget: FlowPane =
    new FlowPane(Orientation.Horizontal):
      hgap = 5
      vgap = 5
      padding = Insets(5,0,5,0)
      children ++= Seq(new BoldLabel("Disastri:"), new ValueLabel("Nessun disastro",10))