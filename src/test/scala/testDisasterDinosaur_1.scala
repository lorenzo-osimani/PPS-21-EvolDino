import it.unibo.pps.evoldino.model.createListDemoDinosaur
import it.unibo.pps.evoldino.model.disaster.DisasterGenerator.{createListOfDisasters, createRandomDisasterWithProb}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers

class testDisasterDinosaur_1 extends AnyFunSpec:

  describe("Tests for createListDemoDinosaur") {
    val testPopolation = createListDemoDinosaur()
    it("should have elements") {
      println("\n DO CREATE LIST DEMO OF DINOSAUR \n")
      println(testPopolation)
      assertResult(false)(testPopolation.isEmpty)
    }
  }

  describe("Tests for createRandomDisasterWithProb") {
    val testDisasterSingleRandomProb = createRandomDisasterWithProb()
    it("should not be empty") {
      println("\n DO SINGLE RANDOM DISASTER WITH PROB \n")
      println(testDisasterSingleRandomProb)
      assertResult(false)(testDisasterSingleRandomProb.equals(()))
    }
  }

  describe("Tests for a create a list with random disaster with probability") {
    val number = 5
    val testList = createListOfDisasters(5)
    it("should have the correct number of elements") {
      println("\n DO LIST RANDOM DISASTER WITH PROB with " + number + " elements\n")
      println(testList)
      assertResult(true)(testList.length == number)
    }
  }
