import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.createDemoDinosaur
import it.unibo.pps.evoldino.model.createDemoDisaster

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

}
