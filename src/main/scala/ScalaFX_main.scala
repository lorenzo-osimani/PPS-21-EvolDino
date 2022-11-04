import javafx.geometry.Pos
import javafx.scene.layout.GridPane
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.geometry.Pos.{TOP_LEFT, TOP_RIGHT, TopLeft, TopRight}
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.*
import scalafx.scene.paint.Color.{White, *}
import scalafx.scene.text.Text
import scalafx.scene.button
import scalafx.scene.gridpane

import scala.language.implicitConversions

object ScalaFX_main extends JFXApp3 :

  override def start(): Unit =
      button1 = new Button("PREMI QUI")
      button2 = new Button("PREMI QUI B")
    button3 = new Button("PREMI QUI C")
    button4 = new Button("PREMI QUI A")
    button5 = new Button("PREMI QUI D")

    stage = new JFXApp3.PrimaryStage :
      width = 1200
      height = 750
      //    initStyle(StageStyle.Unified)
      title = "Epic Dinosaur Evolution Simulator"
      button1 = new Button("PREMI QUI")
      button2 = new Button("PREMI QUI B")
      button3 = new Button("PREMI QUI C")
      button4 = new Button("PREMI QUI A")
      button5 = new Button("PREMI QUI D")


      gridpane = new GridPane()
      gridPane.add(button1, 0, 0)
      gridPane.add(button2, 1, 0)
      gridPane.add(button3, 1, 1)
      gridPane.add(button4, 0, 1)

      scene = new Scene(gripane, 900, 1200):

        fill = Color.rgb(38, 38, 38)
        content = new HBox :
          padding = Insets(10, 10, 10, 10)
          children = Seq(
            new Text :
              text = "Dinosaur"
              style = "-fx-font: italic bold 70pt sans-serif"
              fill = new LinearGradient(endX = 0, stops = Stops(Red, GreenYellow))
              effect = new DropShadow :
                color = White
                radius = 15
                spread = 0.25
          )

/*          children = Seq(

            new Button :
              padding = Insets(10, 10, 500, 300)
              alignment = TopLeft
              posion:

          )*/


    /*        )
            ,

          children = Seq(
            new TableView[Person](characters) :
              columns ++= Seq(
            new TableColumn[Person, String] :
              text = "First Name"
              cellValueFactory = _.value.firstName
            ,
            new TableColumn[Person, String]() :
              text = "Last Name"
              cellValueFactory = _.value.lastName
            ,
            new TableColumn[Person, Color] :
              text = "Favorite Color"
              // What should be used as the value of the cell
              cellValueFactory = _.value.favoriteColor
              // How the value should be displayed in the cell
              cellFactory = (cell, color) => cell.graphic = Circle(fill = color, radius = 8)
              )
            )*/

          //https://javadoc.io/doc/org.scalafx/scalafx_3/16.0.0-R24/api/scalafx/scene/control/Button.html