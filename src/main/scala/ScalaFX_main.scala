import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.*
import scalafx.scene.paint.Color.*
import scalafx.scene.text.Text

import scala.language.implicitConversions

object ScalaFX_main extends JFXApp3 :

  override def start(): Unit =

    stage = new JFXApp3.PrimaryStage :
      //    initStyle(StageStyle.Unified)
      title = "Epic Dinosaur Evelution Simulator"
      scene = new Scene :
        fill = Color.rgb(38, 38, 38)
        content = new HBox :
          padding = Insets(50, 80, 50, 80)
          children = Seq(
            new Text :
              text = "Dinosaur"
              style = "-fx-font: italic bold 70pt sans-serif"
              fill = new LinearGradient(endX = 0, stops = Stops(Red, GreenYellow))
              effect = new DropShadow :
                color = White
                radius = 15
                spread = 0.25
            ,
            new Text :
              text = "Evolution Simulator"
              style = "-fx-font: italic bold 70pt sans-serif"
              fill = new LinearGradient(endX = 0, stops = Stops(Blue, GreenYellow))
              effect = new DropShadow :
                color = Green
                radius = 15
                spread = 0.25
            ,
            new Text :
              text = "Best in the world"
              style = "-fx-font: italic bold 20pt sans-serif"
              fill = new LinearGradient(endX = 0, stops = Stops(White, DarkGray))
              effect = new DropShadow :
                color = Red
                radius = 15
                spread = 0.25
          )