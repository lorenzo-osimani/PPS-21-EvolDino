package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.model.dinosaur.Dinosaur

type Population = Seq[Dinosaur]

object PopulationFactory {

  def apply(size: Int): Population =
    Seq.fill(size)(Dinosaur.randomizedDinosaur())
}
