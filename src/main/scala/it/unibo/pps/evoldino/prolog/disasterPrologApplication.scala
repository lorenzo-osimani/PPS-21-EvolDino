package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import alice.tuprolog.*

object disasterPrologApplication extends App:
  import PrologInScala.{*, given}

  private val fileName = "disasterPrologTheory.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))

  engine("damageDino(doDisaster(dinosaur(DINX, DINY, LIF), disaster(DISX, DISY, EXT, DAM))).") foreach (println(_))
