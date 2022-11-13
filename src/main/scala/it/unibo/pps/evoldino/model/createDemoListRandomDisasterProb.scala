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

/*
private def overrideExtAndPos(d: Disaster): Unit =
  print("ok")
  /*override d.extension = generateRandomExtensionPList()
  override d.coordinates = generateRandomPositionPList()*/
*/
def createDemoListRandomDisasterProb(n: Int): List[Disaster] =
  /*ERRATO RICONTROLLARE**/
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

  val disasterSingleRandomProb = new ListBuffer[Disaster]()

  /*cosi rimane comunque il problema" se genero 15 e ne ho un array di 10 sono fregato" */
  /* SOLUZIONE
  * fai solo la funzione che ne crea uno (cosi eviti anche il casino del match case
  * poi ogni volta richiami tutta la funzione
  */
  //penso sia meglio fare alla fine che crei una funzione per crearne uno. poi la richiami
  for(nDis <- 1 to n)
    disasterSingleRandomProb += disProbList(Random.nextInt(disProbList.length))

  val dTestRandomProb = disasterSingleRandomProb.toList

  return dTestRandomProb


  /*  val
    case class Earthquake(x: Int, y: Int) extends Disaster with AreaEffect :
      override val name = "Earthquake"
      override val damage = 1000
      override val extension = 3
      override val probability = 0.5
      override val coordinates: (Int, Int) = (x, y)
  */
  /*
  var dino1: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erbo1"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int,Int) = (50,50)
    val dinoID: Int = 1

  var dino2: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erbo2"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 2

  var dino3: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erb3"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 2

  var dino4: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "carn4"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 3

  var dino5: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "pollo5"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 5
    
  var dino6: Dinosaur = new Dinosaur with DinosaurTest:
    val kind: String = "erbivorous"
    val name: String = "erbo6"
    val height: Int = 23
    val weight: Int = 1234
    val color: String = "green"
    val gender: String = "male"
    val testAge: Int = 100
    val testLifePoints: Int = 100
    val testCoordinates: (Int, Int) = (50, 50)
    val dinoID: Int = 6

  //usaare listbuffer perche' mutabile//vedere qui
  // https://alvinalexander.com/scala/how-add-elements-to-a-list-in-scala-listbuffer-immutable/
  import scala.collection.mutable.ListBuffer

  var populationTest = new ListBuffer[Dinosaur]()
  populationTest += dino1
  populationTest += dino2
  populationTest += dino3
  populationTest += dino4
  populationTest += dino5
  populationTest += dino6

  val pTest = populationTest.toList
  
  return pTest
  */


/*if(disWithProb == Disaster.Earthquake)
  disWithProb.AreaEffect.apply(generateRandomExtensionPList(),(0,0))
  //disWithProb.Earthquake.
  print("d")
*/
/*
if(disWithProb.extension != None)
disWithProb.extension = generateRandomExtensionPList()
if(isypeOf(disWithProb) == Disaster.Drought
print("sss")
disWithProb(e = generateRandomExtensionPList())
*/

