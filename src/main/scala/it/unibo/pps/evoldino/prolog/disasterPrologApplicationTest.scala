package it.unibo.pps.evoldino.prolog

import alice.tuprolog
import alice.tuprolog.*

object disasterPrologApplicationTest extends App:
  import PrologInScala.{*, given}

  private val fileName = "disasterPrologTheory.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))

  engine("test(dinosaur(DINXEX, DINYEX, LIFEX)).") foreach (println(_))
