import it.unibo.pps.evoldino.model.disaster.Disaster
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers

class DisasterTest extends AnyFunSpec {

  describe("Tests for Earthquake") {
    val earthquakeTest = Disaster.Earthquake()
    it("should have the name Earthquake") {
      assertResult("Earthquake")(earthquakeTest.name)
    }
    it("should have extension") {
      assertCompiles("earthquakeTest.extension")
    }
    it("should have coordinates") {
      assertCompiles("earthquakeTest.coordinates")
    }
    it("should not have temperature") {
      assertDoesNotCompile("earthquakeTest.temperature")
    }
  }

  describe("Tests for Meteorite") {
    val meteoriteTest = Disaster.Meteorite()
    it("should have the name Meteorite") {
      assertResult("Meteorite")(meteoriteTest.name)
    }
    it("should have extension") {
      assertCompiles("meteoriteTest.extension")
    }
    it("should have coordinates") {
      assertCompiles("meteoriteTest.coordinates")
    }
    it("should not have temperature") {
      assertDoesNotCompile("meteoriteTest.temperature")
    }
  }

  describe("Tests for IceAge") {

    val iceAgeTest = Disaster.IceAge
    it("should have the name IceAge") {
      assertResult("Ice Age")(iceAgeTest.name)
    }
    it("should have an environment") {
      assertCompiles("iceAgeTest.environment")
    }
    it("should not have extension") {
      assertDoesNotCompile("iceAgeTest.extension")
    }
    it("should not have coordinates") {
      assertDoesNotCompile("iceAgeTest.coordinates")
    }
  }

  describe("Tests for Drought") {
    val droughtTest = Disaster.Drought
    it("should have the name Drought") {
      assertResult("Drought")(droughtTest.name)
    }
    it("should have an environment") {
      assertCompiles("droughtTest.environment")
    }
    it("should not have extension") {
      assertDoesNotCompile("droughtTest.extension")
    }
    it("should not have coordinates") {
      assertDoesNotCompile("droughtTest.coordinates")
    }
  }

}
