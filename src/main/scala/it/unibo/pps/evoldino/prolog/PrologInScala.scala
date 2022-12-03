package it.unibo.pps.evoldino.prolog

import alice.tuprolog.*
//import it.unibo.pps.evoldino.prolog.PrologInScala

object PrologInScala {

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

object prologFunextends extends App:
  import PrologInScala.{*, given}

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

    same(X, Y) :- dino_position(X, Y), dis_position(X, Y).

    sum ([] , 0) .
    sum ([ H|T], N) :- sum (T , N2 ) , N is H + N2 .

    disPollo(G) :- 50 .

    dis_position_extended(B) :- sum([disPollo(L),10]).

    dis_pos_extended(X,Y) :- sum([(X,Y),10],S1).

    same_extended(X, Y) :- dino_position(X, Y), dis_pos_extended(X,Y).

  """)

  //engine("same(X, Y)") foreach (println(_))
  //val res = engine("same(X, Y)") foreach (println(_))
  //engine("same(X, Y)") foreach (println(_))

  engine("same_extended(X, Y)") foreach (println(_))

  engine("sum([disPollo(G),10],S3)") foreach(println(_))
  //engine("dis_pos_extended(X,Y),S1") foreach(println(_))

  //engine("sum([7,9],S)") foreach(println(_))

  // permutation([1,2,3],[1,2,3]) ... permutation([1,2,3],[3,2,1])

