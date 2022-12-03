package it.unibo.pps.evoldino.model

import alice.tuprolog
import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.disaster.Disaster

import scala.collection.mutable.ListBuffer
import alice.tuprolog.*

import scala.language.postfixOps

object PrologInScala2 {

  def extractTerm(solveInfo: SolveInfo, i: Integer): Term =
    solveInfo.getSolution.asInstanceOf[Struct].getArg(i).getTerm

  def extractTerm(solveInfo: SolveInfo, s: String): Term =
    solveInfo.getTerm(s)

  given Conversion[String, Term] = Term.createTerm(_)

  given Conversion[Seq[_], Term] = _.mkString("[", ",", "]")

  given Conversion[String, Theory] = Theory.parseLazilyWithStandardOperators(_)

  def mkPrologEngine(theory: Theory): Term => LazyList[SolveInfo] =
    val engine = Prolog()
    engine.setTheory(theory)

    goal =>
      new Iterable[SolveInfo] {

        override def iterator = new Iterator[SolveInfo] {
          var solution: Option[SolveInfo] = Some(engine.solve(goal))

          override def hasNext = solution.isDefined &&
            (solution.get.isSuccess || solution.get.hasOpenAlternatives)

          override def next() =
            try solution.get
            finally solution = if (solution.get.hasOpenAlternatives) Some(engine.solveNext()) else None
        }
      }.to(LazyList)


  def solveWithSuccess(engine: Term => LazyList[SolveInfo], goal: Term): Boolean =
    engine(goal).map(_.isSuccess).headOption == Some(true)

  def solveOneAndGetTerm(engine: Term => LazyList[SolveInfo], goal: Term, term: String): Term =
    engine(goal).headOption.map(extractTerm(_, term)).get

}

object prologFunextends2 extends App:
  import PrologInScala2.{*, given}

  val engine: Term => LazyList[SolveInfo] = mkPrologEngine("""
    dino_position(70, 30).
    dino_position(45, 45).
    dino_position(20, 20).
    dino_position(21, 31).
    dino_position(71, 31).
    dino_position(29, 29).
    dino_position(30, 30).

    dis_position(60, 20).
    dis_position(45, 45).
    dis_position(50, 50).
    dis_position(20, 20).

    dis_extension(0).
    dis_extension(10).

  """)

  //engine("same(X, Y)") foreach (println(_))
  //val res = engine("same(X, Y)") foreach (println(_))
  //engine("same(X, Y)") foreach (println(_))

  val ListFromProlog = new ListBuffer[Any]
  val ListFromProlog2 =  new ListBuffer[Any]
  val broccolo1 = engine("dino_position(X,Y)") foreach ( ListFromProlog += _ )
  val broccolo2 = engine("dino_position(X,Y)") foreach ( ListFromProlog2 += _.toString )

  //val mio = engine.apply("dino_position(X,Y)")
  //alice.tuprolog.scriptengine.PrologScriptEngine
  val listp2 = ListFromProlog2.toList

  val lista3 = new ListBuffer[Term]
  val broccolo3 = engine("dino_position(X,Y)") foreach ( lista3 += _.getSolution )

  var ol = new ListBuffer[Any]
  lista3(0).getTerm
  print("ok and see")
  print(lista3(0).getTerm)
  lista3.toList.toString()
  //val lista3 = new ListBuffer[Any]
  //print("dimmi che e' yes")
  //listp2(0)
  print("dimmi che e' valore \n")
  print(listp2(0))
  print(listp2(1))


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

