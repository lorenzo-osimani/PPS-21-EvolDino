import it.unibo.pps.evoldino.controller.engine.{Engine, EngineConstants, EngineController}
import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.world.{Environment, WorldHistory}
import it.unibo.pps.evoldino.view.TestView
import org.scalatest.funspec.AnyFunSpec

class ControllerTest extends AnyFunSpec {

  val n_iterations = 3
  Controller.setView(new TestView())

  it("A simulation should be able to start and end") {
    Controller.startSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Controller.endSimulation()
    assert(WorldHistory.history.size >= n_iterations - 1)
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    assert(WorldHistory.history.size >= n_iterations - 1)
  }

  it("A simulation can be paused") {
    Controller.startSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Controller.pauseSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Controller.endSimulation()
    assert(WorldHistory.history.size <= n_iterations + 1)
  }

  it("A simulation can be unpaused") {
    Controller.startSimulation()
    Controller.pauseSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Controller.unpauseSimulation()
    Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
    Controller.endSimulation()
    assert(WorldHistory.history.size <= n_iterations + 1)
  }

  describe("A manual mode should exist") {
    it("can be set active") {
      assert(!EngineController.isManualModeActive)
      Controller.setManualMode(true)
      assert(EngineController.isManualModeActive)
    }

    it("can modify the current environment and keep it constant") {
      Controller.startSimulation()
      Controller.setManualMode(true)
      Controller.modifyManualClimateSettings(10, 10, 10)
      Thread.sleep(EngineConstants.ITERATION_MS_1X * n_iterations)
      assert(WorldHistory.getLastSnapshot().environment equals Environment(10, 10, 10))
    }
  }
}
