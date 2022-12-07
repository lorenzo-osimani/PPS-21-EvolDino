import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.dinosaur.gene.*
import it.unibo.pps.evoldino.utils.GlobalUtils.chooseBetweenTwo

class GeneTest extends AnyFunSpec {

  describe("Tests for genes") {

    val DinoGene: Gene = Gene(10,60,80, "blue")

    it("idealTemperature should have value 10") {
      assert(DinoGene.idealTemperature == 10)
    }
    it("idealHumidity should have value 60") {
      assert(DinoGene.idealHumidity == 60)
    }
    it ("lifespan should have value 80") {
      assert(DinoGene.lifespan == 80)
    }
    it ("color should be blue") {
      assert(DinoGene.color == "blue")
    }
  }

}
