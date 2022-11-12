package it.unibo.pps.evoldino.model.dinosaur

import scala.model.dinosaur.Dinosaur

object Reproduction {

  class CoupleGendersException
    extends ExplainedException()

  type Population = List[Dinosaur]

  case class Couple(mom: Dinosaur, dad: Dinosaur) {
    def toList: Population = List(mom, dad)
    if (mom.gender != Female || dad.gender != Male) throw CoupleGendersException()
  }

  def combineCouples(dinosaurs: Population): List[Couple] = {
   // val split = dinosaurs partition (_.gender == Female)
   //  split._1.shuffle zip split._2.shuffle map (c => Couple(c._1, c._2))
  }
}
