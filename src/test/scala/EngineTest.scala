import cats.effect.{IO, Temporal}
import controller.engine.Engine
import example_prof.BTrees.Tree.*
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import org.scalatest.matchers.should.Matchers

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration


class EngineTest extends AnyFunSpec {

  describe("A basic engine should start") {
    it ("should cycle") {
      Engine.simulationLoop()
    }
  }
}
