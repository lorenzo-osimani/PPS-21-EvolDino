package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import alice.tuprolog.*

object disasterPrologApplicationTest extends App:
  import PrologInScala.{*, given}

  private val fileName = "disasterPrologTheoryTest.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))

  engine("damageDino(doDisaster(dinosaur(DINX, DINY, LIF), disaster(DISX, DISY, EXT, DAM))).") foreach (println(_))
