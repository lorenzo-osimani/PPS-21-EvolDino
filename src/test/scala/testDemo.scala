import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.createDemoDinosaur
import it.unibo.pps.evoldino.model.createDemoDisaster
import it.unibo.pps.evoldino.model.createDemoSingleRandomDisaster
import it.unibo.pps.evoldino.model.createSingleRandomDisasterProb
import it.unibo.pps.evoldino.model.createList
class testDemo extends AnyFunSpec:

    describe("Tests for createDinosaurDemo") {
      val testPopolation = createDemoDinosaur()
      it("should have elements"){
        print("\n DIN \n")
        println(testPopolation)
        println("\n OK DIN \n")
      }
    }

    describe("Tests for createDisasterDemo Single Random With Prob") {
      val testDisasterSingleRandomProb = createSingleRandomDisasterProb()
      it("should have elements") {
        print("\n DIS SINGLE RANDOM PROB \n")
        println(testDisasterSingleRandomProb)
        println("\n OK DIS SINGLE RANDOM WITH PROB \n")
      }
    }

    describe("Tests for createDisasterDemo List Random With Prob") {
      val testDisasterSingleRandomProb = createList(15)
      it("should have elements") {
        print("\n DIS LIST RANDOM PROB with 15 elements\n")
        println(testDisasterSingleRandomProb)
        println("\n OK DIS LIST RANDOM WITH PROB \n")
  }
}
