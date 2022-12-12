import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.dinosaur.{Dinosaur, Male, Female}
import it.unibo.pps.evoldino.model.dinosaur.gene.*

class DinosaurTest extends AnyFunSpec {

  describe("A dinosaur has a life cycle") {
    val dino: Dinosaur = Dinosaur(Gene(20, 35, 75, "green"), Male, (10, 20))
    it("When the dinosaur has lifepoints >0 is alive") {
      assert(dino.isAlive)
    }

    it("When the dinosaur has lifepoints <0 is not alive") {
      dino.kill()
      assert(!dino.isAlive)
    }
  }

  describe("A dinosaur increases her age") {
    val newDino: Dinosaur = Dinosaur(Gene(35, 10, 50, "brown"), Female, (5,15))
    it("A dinosaur increases her age by 1") {
      assert(newDino.age == 0)
      newDino.incrementAge()
      assert(newDino.age == 1)
    }
  }
}
