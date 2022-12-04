package it.unibo.pps.evoldino.prolog

import alice.tuprolog.*

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
