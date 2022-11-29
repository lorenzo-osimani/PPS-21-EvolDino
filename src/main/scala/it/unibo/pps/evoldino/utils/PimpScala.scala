package it.unibo.pps.evoldino.utils

object PimpScala:

  case class RichInt(value: Int):

    /**
     * This methods keep a value to which a delta is added in a range, going back to 0 if the bound
     * is surpassed
     */
    def keepValueInCircularRange(delta: Int, max: Int): Int =
      val new_value = value + delta
      if (new_value >= max) new_value - max else new_value

  given Conversion[Int, RichInt] = RichInt(_)

  case class RichFloat(value: Float):

    /** This methods gets a value back into a range of min / max */
    def keepValueInBounds(min: Float = 0, max: Float): Float =
      math.min(math.max(value, min), max)

  given Conversion[Float, RichFloat] = RichFloat(_)
