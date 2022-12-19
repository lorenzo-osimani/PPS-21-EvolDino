package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import alice.tuprolog.*
import it.unibo.pps.evoldino.prolog.DisasterPrologApplication.getClass

object ReproductionPrologApplication extends App:
  import PrologInScala.{*, given}

  private val nameFile = "/prolog/ReproductionPrologTheory.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(
    getClass.getResourceAsStream(nameFile)))

  engine("increaseDino(doReproduction(dinosaur(DINX, DINY, SON), reproduction(REPX, REPY, EXT, INC))).") foreach (println(_))
