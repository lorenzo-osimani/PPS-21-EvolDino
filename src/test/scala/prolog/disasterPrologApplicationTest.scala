package prolog

import alice.tuprolog
import alice.tuprolog.*

object disasterPrologApplicationTest extends App:
  import it.unibo.pps.evoldino.prolog.PrologInScala.{*, given}
  
  private val fileName = "disasterPrologTheoryTest.pl"
  val engine: Term => LazyList[SolveInfo] = mkPrologEngine(Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName)))

  engine("%test(dinosaur(DINXEX, DINYEX, LIFEX))") foreach (println(_))
