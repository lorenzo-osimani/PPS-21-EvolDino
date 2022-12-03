package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.model.dinosaur
import it.unibo.pps.evoldino.model.dinosaur.*
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.disaster.Disaster
import it.unibo.pps.evoldino.model.createDemoDinosaur

import scala.collection.mutable.ListBuffer
import alice.tuprolog.*

import scala.language.postfixOps

object disasterApplication extends App:
  import PrologInScala.{*, given}

  val myDinoList = createDemoDinosaur()

  val dinX = new ListBuffer[scala.Int]
  val dinY = new ListBuffer[scala.Int]


  //stampare con una funzione scala style i dinosauri :)
  /*
  myDinoList.zipWithIndex.map {
    case (dino, count) =>
      println(s"$count is $dino")
      dinX += dino.coordinates._1
      dinY += dino.coordinates._2
  }*/

  //i Disasteri per semplicità li ho inventati direttamente dentro Prolog


  //val myPrologFileName = "./disasterPrologTheory.pl"
  //val engine: Term => LazyList[SolveInfo] = mkPrologEngine(myPrologFileName)
  private val fileName = "disasterPrologTheory.pl"
  //private val engine = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))

//engine("same(X, Y)") foreach (println(_))
  //val res = engine("same(X, Y)") foreach (println(_))
  //engine("same(X, Y)") foreach (println(_))
  //engine("same(X, Y)") foreach (println(_))
  engine("doDisaster(dinosaur_position(DINX, DINY), dis_position(DISX, DISY, EXT)).") foreach (println(_))
  //NOTARE CHE UNA IS A LIST OF solveinfo e l altra di term

  //IO HO USATO TUPROLOG IN JAVA, MA PENSO SIA MEGLIO NUOVA VERSIONE IN KOTLIN !!
  //PENSO CHE FORSE MEGLIO SE LA STRINGA DELL' ENGINE SI SPOSTI FUORI NEL FILE .pl e si chiami poi

  /*
  val DinoFromProlog1 = new ListBuffer[SolveInfo]
  val DinoFromProlog2 =  new ListBuffer[Term]

  val termP1 = engine("din_position(X,Y)") foreach ( DinoFromProlog1 += _ )
  val termP2 = engine("din_position(X,Y)") foreach ( DinoFromProlog2 += _.getSolution )

  val DinolistP1 = DinoFromProlog1.toList
  val DinolistP2 = DinoFromProlog2.toList
  */

  //questo e' per prendere e stampare i dinosauri da prolog

  /*
  print("print list dinosaur" + "\n")
   */
  /*
  for (i <- DinolistP1)
    print("list: " + i + "\n")

  print("print list dinosaur sol" + "\n")
  for (i <- DinolistP2)
    print("list sol: " + i + "\n")
  */
  //questo è per prendere e stampare i disastri da prolog

  /*
  val DisFromProlog =  new ListBuffer[Term]
  val DisProlog: Unit = engine("dis_position(X,Y,E)") foreach ( DisFromProlog += _.getSolution )
  val DisList = DisFromProlog.toList

  print("print list disaster sol" + "\n")
  for (i <- DisList)
    print("list sol: " + i + "\n")
  */
  //questo passa la funzione all engine e poi viene stampata
  /*
  val ListFromPrologSameP = new ListBuffer[Term]
  val sameP = engine("same(X,Y)") foreach ( ListFromPrologSameP += _.getSolution )

  val samePList = ListFromPrologSameP.toList

  print("print list solutions" + "\n")
  for (i <- samePList)
    print("list sol: " + i + "\n")

  val lp2 = new ListBuffer[SolveInfo]
  */
  //stessa cosa di sopra ma qui uso la funzione di TuProlog per prendere solo una variabile

  /*
  val SameP2 = engine("same(X,Y)") foreach ( lp2 += _)
  val lp2List= lp2.toList

  lp2List.zipWithIndex.foreach {
    case (el, i) =>
      print("element: " + i + " is " + "\n"
        + "X:" + el.getVarValue("X") + "\n"
        + "Y:" + el.getVarValue("Y") + "\n")
  }*/




  /*
  var X = 0
  var Y = 0
  */

  /*
  for((x,i) <- lp2List.zipWithIndex) print("element:" + i + "is" + x + "\n")
  */




  /*
  for el <- 0 to lp2List.length
    print(lp2(el).getVarVuale("X"))
  */
  //lp2List foreach xsol in lp2List print(lp2List(xsol).getVarValue("X"))
  //lp2List.flatMap(print(lp2(_).getVarValue("X")))
/*  for idx <- (lp2List.length):
    X = lp2(idx).getVarValue("X")
    Y = lp2(idx).getVarValue("Y")
    print("X: " + X + "\n")
    print("Y: " + Y + "\n")*/





//print (lista3(0).extractTerm)












  //val lista3 = listp2.toList
  //lista3 = listp2.flatMap(_ => scala.Int)
  //print(cane)
  //var pollo2 = engine("dino_position(X,Y).")
  //var pollo2 = cane.grouped(1)
  //broccolo2()
  //print(ListFromProlog)
  //val i1 = extractTerm(Term => ,1)
  //print(i1)
  //extractTerm(solveInfo: SolveInfo, i: Integer):
  //val lista =  new Laengine.compose(broccolo2 => mio)

  //print(ListFromProlog)
  //print(List(ListFromProlog))
  /*
  print("SOLUZIONI TROVATE 1 \n")
  for(el <- ListFromProlog)
    print(el)

  print("SOLUZIONI TROVATE 2 \n")
  for (el2 <- ListFromProlog2)
    print(el2)*/
 // engine("sum([disPollo(G),10],S3)") foreach(println(_))
  //engine("dis_pos_extended(X,Y),S1") foreach(println(_))

  //engine("sum([7,9],S)") foreach(println(_))

  // permutation([1,2,3],[1,2,3]) ... permutation([1,2,3],[3,2,1])

