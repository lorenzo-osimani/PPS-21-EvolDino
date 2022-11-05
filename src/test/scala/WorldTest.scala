import cats.effect.{IO, Temporal}
import controller.engine.WorldHistory
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import org.scalatest.matchers.should.Matchers
import controller.engine.WorldHistory.*
import model.Environment

class WorldTest extends AnyFunSpec {

  describe("A basic world should be able to start") {
    it ("should be initialized") {
      assert(WorldHistory.getLastSnapshot() != null)
    }

    it ("should have a basic climate and a population") {
      assert(WorldHistory.getLastSnapshot().environment equals Environment.BasicEnvironment)
      assert(!(WorldHistory.getLastSnapshot().population equals null))
    }

    it ("should evolve") {
      for
        i <- 0 until  10
       do
         WorldHistory.nextIteration()
         assert(!(WorldHistory.getLastSnapshot().environment equals Environment.BasicEnvironment))
    }
  }
}
