import it.unibo.pps.evoldino.controller.engine.WorldHistory.dinosaursEatingPhase
import it.unibo.pps.evoldino.controller.engine.{ Engine, EngineConstants, WorldHistory }
import it.unibo.pps.evoldino.model.Environment
import org.scalatest.funspec.AnyFunSpec

class EngineTest extends AnyFunSpec {

  it("A simulation should be able to start") {
    resetTestConditions()
    Thread.sleep(EngineConstants.iteration_ms_1x * 3)
    Engine.endSimulation()
    assert(WorldHistory.history.size >= 3)
  }

  it("A simulation can be paused") {
    resetTestConditions()
    Thread.sleep(EngineConstants.iteration_ms_1x * 3)
    assert(WorldHistory.history.size >= 3)
    Engine.pauseSimulation()
    Thread.sleep(EngineConstants.iteration_ms_1x * 3)
    Engine.endSimulation()
    assert(WorldHistory.history.size <= 4)
  }

  it("A simulation can be unpaused") {
    resetTestConditions()
    Engine.pauseSimulation()
    Thread.sleep(EngineConstants.iteration_ms_1x * 3)
    assert(WorldHistory.history.size <= 2)
    Engine.unpauseSimulation()
    Thread.sleep(EngineConstants.iteration_ms_1x * 3)
    Engine.endSimulation()
    assert(WorldHistory.history.size >= 3)
  }

  def resetTestConditions(): Unit = {
    WorldHistory.resetHistory()
    Engine.startSimulation()
  }
}
