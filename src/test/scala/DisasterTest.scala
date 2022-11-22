import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.disaster.Disaster

class DisasterTest extends AnyFunSpec {
  print("test begin")

  describe("Tests for IceAge") {

    val iceAgeTest = Disaster.IceAge
    it("should have the name IceAge") {
      assertResult("IceAge")(iceAgeTest.name)
    }
    it("should have extension 0") {
      assertDoesNotCompile("iceAgeTest.extension")
    }
    it("should have coordinates 0,0") {
      assertDoesNotCompile("iceAgeTest.coordinates")
    }
  }

  describe("Tests for Earthquake") {
    val earthquakeTest = Disaster.Earthquake(0, (0, 0))
    it("should have the name Earthquake") {
      assertResult("Earthquake")(earthquakeTest.name)
    }
    it("should have temperature 0") {
      assertDoesNotCompile("earthquakeTest.temperature")
    }
  }

  describe("Tests for Meteorite") {
    val meteoriteTest = Disaster.Meteorite(0, (0, 0))
    it("should have the name Meteorite") {
      assertResult("Meteorite")(meteoriteTest.name)
    }
    it("should have temperature 0") {
      assertDoesNotCompile("meteoriteTest.temperature")
    }
  }

  describe("Tests for Drought") {
    val droughtTest = Disaster.Drought
    it("should have the name Drought") {
      assertResult("Drought")(droughtTest.name)
    }
    it("should have extension 0") {
      assertDoesNotCompile("droughtTest.extension")
    }
    it("should have coordinates 0,0") {
      assertDoesNotCompile("droughtTest.coordinates")
    }
  }

  // it ("a dinosaur Die")
  //  assert
  /*
  it ("a disaster created") {
    assertResult(not(Error))
    assertThrows(Error)
    assert((assertResult(Error) createRadnomDisaster()) == false)
  }*/

  // oppure creare cosa per cui assert ture if no bodu is created disaster

  //  fare test conn gli optional ??
  //  cioè se alcuni diastry non hanno quell optional allora Nil = true e gli altri true
  /*    it ("should cointain 2 but not 3") {
        assert(find(tree, 2) == true)
        assert(find(tree, 3) == false)
      }

      it ("should have 1 element of 2 and 2 elements of 1") {
        assertResult(2) {count(tree, 1)}
        assertResult(1) {count(tree, 2)}
        assertResult(0) {count(tree, 3)}
      }*/

  /*   it("has no value void except Optionals") {
       assertFalse()

     }
       assertResult(Nil) {count(tree, 1)
     }

   */
}
