package it.unibo.pps.evoldino.controller.engine

import cats.effect.{ ExitCode, IO, IOApp, Temporal }
import cats.effect.IO.unit
import cats.effect.unsafe.implicits.global

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration
import WorldHistory.*
import it.unibo.pps.evoldino.controller.engine.Engine.iterationLoop

object Engine {

  private var iteration_speed = EngineConstants.iteration_ms_1x

  private var paused = false

  private var ended = false

  def startSimulation(): Unit = simulationLoop().unsafeRunAndForget()

  def endSimulation(): Unit = ended = true

  def pauseSimulation(): Unit = paused = true

  def unpauseSimulation(): Unit =
    paused = false
    simulationLoop().unsafeRunAndForget()

  def changeSpeed(): Unit = iteration_speed match
    case EngineConstants.iteration_ms_1x => iteration_speed = EngineConstants.iteration_ms_2x
    case EngineConstants.iteration_ms_2x => iteration_speed = EngineConstants.iteration_ms_4x
    case EngineConstants.iteration_ms_4x => iteration_speed = EngineConstants.iteration_ms_1x

  given Conversion[Unit, IO[Unit]] with
    def apply(exp: Unit): IO[Unit] = IO(exp)

  private def simulationLoop(): IO[Unit] = for {
    _ <- Temporal[IO].sleep(FiniteDuration.apply(iteration_speed, TimeUnit.MILLISECONDS))
    _ <- iterationLoop()
    _ <- if (!hasSimulationEnded() && !paused) simulationLoop() else unit
  } yield ()

  private def iterationLoop(): IO[Unit] = for {
    _ <- nextIteration()
    _ <- dinosaursEatingPhase()
    _ <- applyDisturbances()
    _ <- reproductionPhase()
  } yield ()

  private def hasSimulationEnded(): Boolean = isSimulationOver() || ended

}
