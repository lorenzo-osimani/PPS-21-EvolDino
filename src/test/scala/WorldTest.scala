import cats.effect.{ IO, Temporal }
import it.unibo.pps.evoldino.controller.engine.WorldHistory
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import org.scalatest.matchers.should.Matchers
import it.unibo.pps.evoldino.controller.engine.WorldHistory.*
import it.unibo.pps.evoldino.model.world.Environment

class WorldTest extends AnyFunSpec {

  describe("A basic world should be able to start") {
    it("should be initialized") {
      assert(WorldHistory.getLastSnapshot() != null)
    }

    it("should have a basic climate and a population") {
      assert(WorldHistory.getLastSnapshot().environment.isInstanceOf[Environment])
      assert(WorldHistory.getLastSnapshot().population != null)
    }
  }
}
