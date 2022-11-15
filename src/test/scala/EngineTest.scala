import it.unibo.pps.evoldino.controller.engine.WorldHistory.dinosaursEatingPhase
import it.unibo.pps.evoldino.controller.engine.{
  Engine,
  EngineConstants,
  EngineController,
  WorldHistory
}
import it.unibo.pps.evoldino.model.Environment
import org.scalatest.funspec.AnyFunSpec

class EngineTest extends AnyFunSpec {

  val n_iterations = 3

  it("A simulation should be able to start") {
    resetTestConditions()
    Thread.sleep(EngineConstants.iteration_ms_1x * n_iterations)
    Engine.endSimulation()
    assert(WorldHistory.history.size >= n_iterations)
  }

  it("A simulation can be paused") {
    resetTestConditions()
    Thread.sleep(EngineConstants.iteration_ms_1x * n_iterations)
    Engine.pauseSimulation()
    Thread.sleep(EngineConstants.iteration_ms_1x * n_iterations)
    Engine.endSimulation()
    assert(WorldHistory.history.size <= n_iterations + 1)
  }

  it("A simulation can be unpaused") {
    resetTestConditions()
    Engine.pauseSimulation()
    Thread.sleep(EngineConstants.iteration_ms_1x * n_iterations)
    Engine.unpauseSimulation()
    Thread.sleep(EngineConstants.iteration_ms_1x * n_iterations)
    Engine.endSimulation()
    assert(WorldHistory.history.size >= n_iterations)
  }

  private def resetTestConditions(): Unit = {
    WorldHistory.resetHistory()
    Engine.startSimulation()
  }

  describe("A manual mode should exist") {
    it("can be set active") {
      assert(!EngineController.isManualModeActive())
      EngineController.setManualMode(true)
      assert(EngineController.isManualModeActive())
    }

    it("can modify the current environment and keep it constant") {
      EngineController.setManualMode(true)
      EngineController.modifyManualSettings(10, 10, 10)
      resetTestConditions()
      Thread.sleep(EngineConstants.iteration_ms_1x * n_iterations)
      assert(WorldHistory.getLastSnapshot().environment equals Environment(10, 10, 10))
    }
  }
}
