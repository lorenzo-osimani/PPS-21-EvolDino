import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.createDemoDinosaur
import it.unibo.pps.evoldino.model.disaster.DisasterGenerator.{
  createListOfDisasters,
  createSingleRandomDisasterProb
}

class testDemo extends AnyFunSpec:

  describe("Tests for createDinosaurDemo") {
    val testPopolation = createDemoDinosaur()
    it("should have elements") {
      print("\n DIN \n")
      println(testPopolation)
      println("\n OK DIN \n")
      assertResult(false)(testPopolation.isEmpty)
    }
  }

  describe("Tests for createDisasterDemo Single Random With Prob") {
    val testDisasterSingleRandomProb = createSingleRandomDisasterProb()
    it("should have elements") {
      print("\n DIS SINGLE RANDOM PROB \n")
      println(testDisasterSingleRandomProb)
      println("\n OK DIS SINGLE RANDOM WITH PROB \n")
      assertResult(false)(testDisasterSingleRandomProb.equals(()))
    }
  }

  describe("Tests for createDisasterDemo List Random With Prob") {
    val number = 5
    val testList = createListOfDisasters(5)
    it("should have elements") {
      print("\n DIS LIST RANDOM PROB with " + number + " elements\n")
      println(testList)
      println("\n OK DIS LIST RANDOM WITH PROB \n")
      assertResult(true)(testList.length == number)
    }

  }
