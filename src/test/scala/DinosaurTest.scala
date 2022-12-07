import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.dinosaur.{Dinosaur, Male, Female}
import it.unibo.pps.evoldino.model.dinosaur.gene.*

class DinosaurTest extends AnyFunSpec {

  val dino: Dinosaur = Dinosaur(Gene(20, 35, 75, "green"), Male, (10, 20))
  dino.isAlive: Boolean

  describe("A dinosaur is alive") {
    it("When the dinosaur has lifepoints >0 is alive") {
      assert(dino.isAlive)
    }
  }

  dino.kill(): Unit
  describe("A dinosaur is dead") {
    it("When the dinosaur has lifepoints <0 is not alive") {
      assert(!dino.isAlive)
    }
  }

  val newDino: Dinosaur = Dinosaur(Gene(35, 10, 50, "brown"), Female, (5,15))
  newDino.age == 10
  newDino.incrementAge()

  describe("A dinosaur increases her age") {
    it("A dinosaur increases her age by 1") {
      assert(newDino.age == 11)
    }
  }

}
