package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import alice.tuprolog.*

object DisasterPrologApplication extends App:
  import PrologInScala.{*, given}

  private val fileName = "disasterPrologTheory.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))

  engine("damageDino(dinosaur(DINX, DINY, LIF)).") foreach (println(_))
