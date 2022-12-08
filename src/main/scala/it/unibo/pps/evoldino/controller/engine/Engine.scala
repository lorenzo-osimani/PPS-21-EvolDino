package it.unibo.pps.evoldino.controller.engine

import cats.effect.{ ExitCode, IO, IOApp, Temporal }
import cats.effect.IO.unit
import cats.effect.unsafe.implicits.global

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration
import it.unibo.pps.evoldino.model.world.WorldHistory.*
import it.unibo.pps.evoldino.controller.engine.Engine.iterationLoop
import it.unibo.pps.evoldino.controller.Controller
import EngineConstants.*
import it.unibo.pps.evoldino.model.dinosaur.Male
import it.unibo.pps.evoldino.model.world.WorldHistory

object Engine {

  private var iteration_speed: Int = ITERATION_MS_1X

  private var paused = false

  private var ended = false

  def startSimulation(): Unit =
    paused = false
    ended = false
    EngineController.resetController()
    WorldHistory.resetHistory()
    Controller.renderIteration(WorldHistory.getLastSnapshot())
    simulationLoop().unsafeRunAndForget()

  def doSingleIteration(): Unit =
    iterationLoop().unsafeRunAndForget()

  def endSimulation(): Unit = ended = true

  def pauseSimulation(): Unit = paused = true

  def unpauseSimulation(): Unit =
    paused = false
    simulationLoop().unsafeRunAndForget()

  def changeSpeed(): Int =
    iteration_speed match
      case ITERATION_MS_1X => iteration_speed = ITERATION_MS_2X
      case ITERATION_MS_2X => iteration_speed = ITERATION_MS_4X
      case ITERATION_MS_4X => iteration_speed = ITERATION_MS_1X
    iteration_speed

  def isSimulationPlaying(): Boolean = !paused

  def hasSimulationEnded(): Boolean = isSimulationOver() || ended

  given Conversion[Unit, IO[Unit]] with
    def apply(exp: Unit): IO[Unit] = IO(exp)

  private def simulationLoop(): IO[Unit] = for {
    _ <- Temporal[IO].sleep(FiniteDuration.apply(iteration_speed, TimeUnit.MILLISECONDS))
    _ <- iterationLoop()
    _ <- if (hasSimulationEnded() || paused) unit else simulationLoop()
  } yield ()

  private def iterationLoop(): IO[Unit] = for {
    _ <- nextIteration()
    _ <- dinosaursEatingPhase()
    _ <- applyDisturbances()
    _ <- reproductionPhase()
    _ <- Controller.renderIteration(WorldHistory.getLastSnapshot())
    _ <- if (isSimulationOver())
      (getLastSnapshot().livingPopulation().size <= 0) match
        case true  => Controller.showEndDialog("All the dinosaurs are dead")
        case false => Controller.showEndDialog("The world has reached its maximum lifespan")
  } yield ()

}
