package it.unibo.pps.evoldino.model

import it.unibo.pps.evoldino.model
import it.unibo.pps.evoldino.model.Disaster
import it.unibo.pps.evoldino.model.Disaster.{Drought, Earthquake, Meteorite}
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.createSingleRandomDisasterProb

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
import scala.util.Random

def createList(n: Int) : List[Disaster] =

  val dL = new ListBuffer[Disaster]
  for (i <- 1 to n)
    dL += createSingleRandomDisasterProb()

  val dTestRandomProb = dL.toList

  dTestRandomProb
