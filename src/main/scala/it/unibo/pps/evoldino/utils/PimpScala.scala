package it.unibo.pps.evoldino.utils

object PimpScala:

  case class RichInt(value: Int):

    def keepValueInBounds(delta: Int, max: Int): Int =
      val new_value = value + delta
      if (new_value >= max) new_value - max else new_value

  given Conversion[Int, RichInt] = RichInt(_)
