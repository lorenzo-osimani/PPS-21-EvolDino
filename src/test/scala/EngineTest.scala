import it.unibo.pps.evoldino.controller.engine.{Engine, EngineConstants, EngineController}
import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.world.{Environment, WorldHistory}
import it.unibo.pps.evoldino.view.TestView
import org.scalatest.funspec.AnyFunSpec

class EngineTest extends AnyFunSpec {

  val n_iterations = 3
  Controller.setView(new TestView())

  it("A simulation should be able to start and end") {
    Engine.startSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Engine.endSimulation()
    assert(WorldHistory.history.size >= n_iterations - 1)
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    assert(WorldHistory.history.size >= n_iterations - 1)
  }

  it("A simulation can be paused") {
    Engine.startSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Engine.pauseSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Engine.endSimulation()
    assert(WorldHistory.history.size <= n_iterations + 1)
  }

  it("A simulation can be unpaused") {
    Engine.startSimulation()
    Engine.pauseSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Engine.unpauseSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Engine.endSimulation()
    assert(WorldHistory.history.size <= n_iterations + 1)
  }

  describe("A manual mode should exist") {
    it("can be set active") {
      assert(!EngineController.isManualModeActive())
      EngineController.setManualMode(true)
      assert(EngineController.isManualModeActive())
    }

    it("can modify the current environment and keep it constant") {
      Engine.startSimulation()
      EngineController.setManualMode(true)
      EngineController.modifyManualSettings(10, 10, 10)
      Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
      assert(WorldHistory.getLastSnapshot().environment equals Environment(10, 10, 10))
    }
  }
}
