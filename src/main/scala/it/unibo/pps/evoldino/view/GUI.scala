package it.unibo.pps.evoldino.view

import it.unibo.pps.evoldino.model.disaster.AreaEffect
import it.unibo.pps.evoldino.model.world.WorldSnapshot
import it.unibo.pps.evoldino.view.widgets.ControlBar.{changeSpeedButton, playButton, stopButton}
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{Background, BackgroundFill, BorderPane, FlowPane, TilePane}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight, TextAlignment}
import scalafx.stage.{Screen, Stage}
import it.unibo.pps.evoldino.view.widgets.{ClimateWidget, ControlBar, DisastersControllerWidget, DisastersLogWidget, SimInfoWidget, WorldGridWidget}
import it.unibo.pps.evoldino.view.components.GenericIcon
import scalafx.application.JFXApp3
import scalafx.scene.image.{Image, ImageView}

object GUI:

  def start(stage: Stage): Unit =
    val titleLabel = new Label:
      text = "Evoldino"
      font = Font.font("Arial", FontWeight.Bold, 24)

    val title = new TilePane:
      padding = Insets(5, -15, 5, -15)
      background = new Background(Array(new BackgroundFill(Color.DarkGray, null, null)))
      children ++= Seq(
        GenericIcon("dinosaur.png", "", 40),
        titleLabel)

    stage.setTitle("Evoldino")
    stage.setResizable(false)
    stage.setScene(
      new Scene:
        root = new BorderPane:
          background = new Background(Array(new BackgroundFill(Color.Black, null, null)))
          padding = Insets(10)
          top = title

          bottom = ControlBar.controlBar
          left = new BorderPane:
            top = SimInfoWidget.simInfoWidget
            bottom = WorldGridWidget.worldGridWidget
          right = new FlowPane(Orientation.Vertical):
            background = new Background(Array(new BackgroundFill(Color.Gray, null, null)))
            children ++= Seq(
              DisastersControllerWidget.disastersWidget,
              ClimateWidget.climateWidget
              )
    )

  def updateRender(snapshot: WorldSnapshot): Unit =
    SimInfoWidget.updateRender(snapshot)
    WorldGridWidget.renderWorld(
      snapshot.livingPopulation().map(din => (din.coordinates, din.genes.color)).toMap,
      snapshot.disasters
        .filter(_.isInstanceOf[AreaEffect])
        .map { d =>
          val dis = d.asInstanceOf[AreaEffect]
          (dis.coordinates, dis.extension, dis.name)
        }
    )
    DisastersLogWidget.update(snapshot.disasters.map(_.name))
    ClimateWidget.update(
      snapshot.environment.temperature,
      snapshot.environment.humidity,
      snapshot.environment.vegetationAvailable
    )


