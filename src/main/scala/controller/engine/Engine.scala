package controller.engine

import cats.effect.{IO, IOApp, Temporal, ExitCode}
import cats.effect.IO.unit

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration

import WorldHistory._

object Engine {

  def simulationLoop(): IO[Unit] = for {
    _ <- Temporal[IO].sleep(FiniteDuration.apply(EngineConstants.iteration_ms_1x, TimeUnit.MILLISECONDS))
    _ <- nextIteration()
    _ <-  if (!hasSimulationEnded()) simulationLoop()
      else unit
  } yield ()

  private def hasSimulationEnded(): Boolean = false

 given Conversion[Unit, IO[Unit]] = exp => IO { exp }
}
