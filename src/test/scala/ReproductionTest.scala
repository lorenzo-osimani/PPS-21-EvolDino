import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.dinosaur.*
import it.unibo.pps.evoldino.model.dinosaur.gene.*

class ReproductionTest extends AnyFunSpec {

  val reproductionTest: Population = Seq.empty
  val son = Reproduction.reproduction(reproductionTest)

  describe ("Should be empty if there are not dinosaurs") {
    it ("Should be zero if there is not even a dinosaur") {
      assert(son.isEmpty)
    }
  }

 val add = Seq.fill(1)(Dinosaur)
  val child = Reproduction.reproduction(reproductionTest)
  describe("Should be empty if there is one dinosaurs") {
    it("Should be zero if there is only a dinosaur") {
      assert(child.isEmpty)
    }
  }

}
