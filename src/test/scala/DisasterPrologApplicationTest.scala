import alice.tuprolog.{SolveInfo, Term, Theory}
import it.unibo.pps.evoldino.prolog.PrologInScala
import it.unibo.pps.evoldino.prolog.DisasterPrologApplication
import it.unibo.pps.evoldino.prolog.DisasterPrologApplication.engine
import org.scalatest.funspec.AnyFunSpec
import PrologInScala.{*, given}

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps


class DisasterPrologApplicationTest extends AnyFunSpec:

    describe("The prolog file should be readable") {
      val test_values = ListBuffer(("60", "20", "90"), ("63", "27", "100"),
        ("67", "23", "100"), ("65", "25", "80"), ("57", "15", "90"),
        ("70", "30", "90"), ("45", "45", "100"), ("30", "30", "100"), ("50", "50", "70"), ("90", "90", "60"))

      it("It should return the damaged dinos") {
        val dinX = new ListBuffer[String]
        val dinY = new ListBuffer[String]
        val lif = new ListBuffer[String]

        engine("test(dinosaur(DINX, DINY, LIF)).") foreach(
          dinX += _.getVarValue("DINX").toString
            /*_.getVarValue("DINX"),
          dinY += _.getVarValue("DINY"),
          lif += _.getVarValue("LIF")*/
        )
        assertResult(true)(test_values.eq(dinX))
      }
    }


