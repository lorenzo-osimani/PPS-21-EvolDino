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
import scalafx.scene.image.{Image, ImageView}

object GUI:

  def start(stage: Stage): Unit =

    println("Screen size: " + Screen.primary.bounds.width + "x" + Screen.primary.bounds.height)

    val preferredHeight: Double = Screen.primary.bounds.height * 3 / 4
    val preferredWidth: Double = Screen.primary.bounds.width * 3 / 4

    val titleLabel = new Label:
      text = "Evoldino"
      margin = Insets(0, 0, 0, 0)
      font = Font.font("Arial", FontWeight.Bold, 24)

    val title = new TilePane:
      padding = Insets(5, -15, 5, -15)
      background = new Background(Array(new BackgroundFill(Color.DarkGray, null, null)))
      children ++= Seq(
        GenericIcon("dinosaur.png", "", 40),
        titleLabel)

    stage.setTitle("Evoldino")
    stage.setResizable(true)
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
            children ++= Seq(
              DisastersControllerWidget.disastersWidget,
              ClimateWidget.climateWidget)
    )

    stage.setFullScreen(false)

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
