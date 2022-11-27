package it.unibo.pps.evoldino.model.disaster

import it.unibo.pps.evoldino.model.disaster.Disaster._

import scala.collection.mutable.ListBuffer
import scala.util.Random

object DisasterGenerator {

  val MIN_EXTENSION = 0
  val MAX_EXTENSION = 11
  val MIN_POSITION = 1
  val MAX_POSITION = 101

  private val random = new scala.util.Random(System.currentTimeMillis())

  private def generateRandomExtensionPList(): Int =
    /* genera una estensione random da 0 a 10 */
    random.between(MIN_EXTENSION, MAX_EXTENSION)

  private def generateRandomPositionPList(): (Int, Int) =
    /* genera una posizione random da 1 a 100 */
    (random.between(MIN_POSITION, MAX_POSITION), random.between(MIN_POSITION, MAX_POSITION))

  def createSingleRandomDisasterProb(): Disaster =
    val demoDisEarthquake: AreaEffect = Disaster.Earthquake(e = 0, c = (0, 0))
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
      for (prob <- 1 to disWithProb.probability)
        disWithProb match {
          case _: Earthquake =>
            disProbList += Earthquake(
              e = generateRandomExtensionPList(),
              c = generateRandomPositionPList()
            )
          case _: Meteorite =>
            disProbList += Meteorite(
              e = generateRandomExtensionPList(),
              c = generateRandomPositionPList()
            )
          case _: ClimateEffect =>
            disProbList += disWithProb
          case null => println("ERROR")
        }

    val disasterSingleRandomProb = disProbList(Random.nextInt(disProbList.length))

    disasterSingleRandomProb

  /* It can generate from 0 to 3 disasters */
  def createListOfDisasters(n: Int): List[Disaster] =
    val dL = new ListBuffer[Disaster]
    for (i <- 1 to n)
      dL += createSingleRandomDisasterProb()
    dL.toList

  def createListOfDisastersWithDistribuition(): List[Disaster] =
    val number_of_disasters = (3 * Math.pow(random.nextDouble(), 4)).toInt
    createListOfDisasters(number_of_disasters)
}
