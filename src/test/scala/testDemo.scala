import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.createDemoDinosaur
import it.unibo.pps.evoldino.model.createDemoDisaster
import it.unibo.pps.evoldino.model.createDemoSingleRandomDisaster
import it.unibo.pps.evoldino.model.createDemoSingleRandomDisasterProb
class testDemo extends AnyFunSpec{

    print("test begin")

    describe("Tests for createDinosaurDemo") {
      val testPopolation = createDemoDinosaur()
      it("should have elements"){
        println(testPopolation)
        println("OK DIN")
      }
    }

    describe("Tests for createDisasterDemo") {
      val testDisaster = createDemoDisaster()
      it("should have elements") {
        println(testDisaster)
        println("OK DIS")
      }
    }

    describe("Tests for createDisasterDemo Single Random") {
      val testDisasterSingleRandom = createDemoSingleRandomDisaster()
      it("should have elements") {
        println(testDisasterSingleRandom)
        println("OK DIS SINGLE RANDOM")
      }
    }

    describe("Tests for createDisasterDemo Single Random With Prob") {
      val testDisasterSingleRandomProb = createDemoSingleRandomDisasterProb()
      it("should have elements") {
        println(testDisasterSingleRandomProb)
        println("OK DIS SINGLE RANDOM WITH PROB")
    }
  }

}
