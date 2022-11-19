package it.unibo.pps.evoldino.view

import it.unibo.pps.evoldino.view.ScalaFX_main.stage
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.{ color, GreenYellow, Red, White }
import scalafx.scene.paint.{ Color, LinearGradient, Stops }
import scalafx.scene.text.Text

object ScalaFX_main extends JFXApp3:

  override def start(): Unit =
    /*
    buuton1 = new Button("premi qui A")
    button2 = new Button("PREMI QUI B")
    button3 = new Button("PREMI QUI C")
    button4 = new Button("PREMI QUI A")
    button5 = new Button("PREMI QUI D")

    stage = new JFXApp3.PrimaryStage :
      width = 1200
      height = 750
      //    initStyle(StageStyle.Unified)
      title = "Epic Dinosaur Evolution Simulator"
      button1 = Button("PREMI QUI")
      button2 = new Button("PREMI QUI B")
      button3 = new Button("PREMI QUI C")
      button4 = new Button("PREMI QUI A")
      button5 = new Button("PREMI QUI D")

      scene = new Scene(gripane, 900, 1200):

      gridpane = new GridPane()
      gridPane.add(button1, 0, 0)
      gridPane.add(button2, 1, 0)
      gridPane.add(button3, 1, 1)
      gridPane.add(button4, 0, 1)
     */
    stage = new JFXApp3.PrimaryStage:
      width = 1200
      height = 750
      //    initStyle(StageStyle.Unified)
      title = "Epic Dinosaur Evolution Simulator"

      scene = new Scene(900, 1200):

        fill = Color.rgb(38, 38, 38)

        content = new HBox:
          padding = Insets(10, 10, 10, 10)

          children = Seq(new Text:
            text = "Dinosaur"
            style = "-fx-font: italic bold 70pt sans-serif"
            fill = new LinearGradient(endX = 0, stops = Stops(Red, GreenYellow))

            effect = new DropShadow:
              color = White
              radius = 15
              spread = 0.25
          )
