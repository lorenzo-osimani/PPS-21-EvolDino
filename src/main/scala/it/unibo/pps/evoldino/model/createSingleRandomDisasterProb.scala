package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.model
import it.unibo.pps.evoldino.model.Disaster
import it.unibo.pps.evoldino.model.Disaster.{Drought, Earthquake, Meteorite}
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
import scala.util.Random

private def generateRandomExtensionPList(): Int =
  /* genera una estensione random da 0 a 10 */
  scala.util.Random.between(0,11)

private def generateRandomPositionPList(): (Int,Int) =
  /* genera una posizione random da 1 a 100 */
  (scala.util.Random.between(1,101),scala.util.Random.between(1,101))

def createSingleRandomDisasterProb(): Disaster =
  
  val demoDisEarthquake: AreaEffect = Disaster.Earthquake(e = 0, c = (0,0))
  val demoDisMeteorite: AreaEffect = Disaster.Meteorite(e = 0, c = (0, 0))
  val demoDisIceAge: ClimateEffect = Disaster.IceAge
  val demoDisDrought: ClimateEffect = Disaster.Drought

  val disArrayNoProb = new ListBuffer[Disaster]
  disArrayNoProb += demoDisEarthquake
  disArrayNoProb += demoDisMeteorite
  disArrayNoProb += demoDisIceAge
  disArrayNoProb += demoDisDrought

  val disProbList = new ListBuffer[Disaster]

  for (disWithProb <- disArrayNoProb)
      print("\n EVALUATING: " + disWithProb.name + " con prob " + disWithProb.probability)
      print("\n ")
      for ( prob <- 1 to disWithProb.probability)
        disWithProb match {
          case _: Earthquake =>
            println ("nuovo terremoto \n")
            disProbList += Earthquake(e = generateRandomExtensionPList (), c = generateRandomPositionPList())
          case _: Meteorite =>
            println("nuovo meteorite \n")
            disProbList += Meteorite(e = generateRandomExtensionPList(), c = generateRandomPositionPList())
          case _: ClimateEffect =>
            println("inserito \n")
            disProbList += disWithProb
          case null => println("ERROR")
        }

  print("\n LIST WITH PROB \n")
  print(disProbList)
  print("\n END LIST WITH PROB \n")

  val disasterSingleRandomProb = disProbList(Random.nextInt(disProbList.length))

  disasterSingleRandomProb