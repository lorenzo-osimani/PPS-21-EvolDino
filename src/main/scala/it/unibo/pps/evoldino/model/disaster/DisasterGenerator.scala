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

  def createRandomDisasterWithProb(): Disaster =

    val disListWithProb = new ListBuffer[Disaster]

    for
      disWithProb <- DisasterType.values
      _ <- 1 to disWithProb.probability
    yield
        disWithProb match {
          case DisasterType.EARTHQUAKE =>
            disListWithProb += Earthquake()
          case DisasterType.METEORITE =>
            disListWithProb += Meteorite()
          case DisasterType.ICEAGE =>
            disListWithProb += IceAge
          case DisasterType.DROUGHT =>
            disListWithProb += Drought
          case null => println("ERROR")
        }

    disListWithProb(Random.nextInt(disListWithProb.length))

  /* It can generate from 0 to n disasters */
  def createListOfDisasters(n: Int): List[Disaster] =
    val dL = new ListBuffer[Disaster]
    for (_ <- 1 to n)
      dL += createRandomDisasterWithProb()
    dL.toList
  
  def createListOfDisastersWithDistribuition(): List[Disaster] =
    val number_of_disasters = (3 * Math.pow(random.nextDouble(), 4)).toInt
    createListOfDisasters(number_of_disasters)
}
