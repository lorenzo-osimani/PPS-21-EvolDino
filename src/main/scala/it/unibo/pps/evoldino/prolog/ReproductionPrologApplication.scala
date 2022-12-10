package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import alice.tuprolog.*

object ReproductionPrologApplication extends App:
  import PrologInScala.{*, given}

  private val nameFile = "ReproductionPrologTheory.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(nameFile)))

  engine("increaseDino(doReproduction(dinosaur(DINX, DINY, SON), reproduction(REPX, REPY, EXT, INC))).") foreach (println(_))
