import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.createDemoDinosaur
import it.unibo.pps.evoldino.model.createDemoDisaster
import it.unibo.pps.evoldino.model.createDemoSingleRandomDisaster
import it.unibo.pps.evoldino.model.createDemoSingleRandomDisasterProb

class testDemo extends AnyFunSpec:

    describe("Tests for createDinosaurDemo") {
      val testPopolation = createDemoDinosaur()
      it("should have elements"){
        print("\n DIN \n")
        println(testPopolation)
        println("\n OK DIN \n")
      }
    }

    describe("Tests for createDisasterDemo") {
      val testDisaster = createDemoDisaster()
      it("should have elements") {
        print("\n DIS \n")
        println(testDisaster)
        println("\n OK DIS \n")
      }
    }

    describe("Tests for createDisasterDemo Single Random") {
      val testDisasterSingleRandom = createDemoSingleRandomDisaster()
      it("should have elements") {
        print("\n DIS SINGLE RANDOM \n")
        println(testDisasterSingleRandom)
        println("\n OK DIS SINGLE RANDOM \n")
      }
    }

    describe("Tests for createDisasterDemo Single Random With Prob") {
      val testDisasterSingleRandomProb = createDemoSingleRandomDisasterProb()
      it("should have elements") {
        print("\n DIS SINGLE RANDOM PROB \n")
        println(testDisasterSingleRandomProb)
        println("\n OK DIS SINGLE RANDOM WITH PROB \n")
      }
    }
