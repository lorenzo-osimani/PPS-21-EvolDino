import alice.tuprolog.{SolveInfo, Term, Theory}
import it.unibo.pps.evoldino.prolog.PrologInScala
import it.unibo.pps.evoldino.prolog.DisasterPrologApplication
import it.unibo.pps.evoldino.prolog.DisasterPrologApplication.engine
import org.scalatest.funspec.AnyFunSpec

class DisasterPrologApplicationTest extends AnyFunSpec:
  import PrologInScala.{ *, given }

  describe("The prolog file should be readable") {
    val test_values = Seq(("60","20","90"), ("63","27","100"),
      ("67","23","100"), ("65","25","80"), ("57","15","90"),
      ("70","30","90"), ("45","45","100"), ("30","30","100"), ("50","50","70"), ("90","90","60"))

    it("It should return the damaged dinos") {
            val grouped_values = engine("test(dinosaur(DINXEX, DINYEX, LIFEX)).").map(element =>
              (element.getVarValue("DINXEX").toString,
                element.getVarValue("DINYEX").toString,
                element.getVarValue("LIFEX").toString)
      )
      assert(true, grouped_values.equals(test_values))
    }

  }
