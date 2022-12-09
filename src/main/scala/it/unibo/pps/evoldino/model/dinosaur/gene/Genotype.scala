package it.unibo.pps.evoldino.model.dinosaur.gene

sealed trait Genotype

object LifeSpan extends Genotype:
  val SHORT_LIFE: Int = 50
  val MEDIUM_LIFE: Int = 75
  val LONG_LIFE: Int = 100

object TemperatureType extends Genotype:
  val COLD_TEMPERATURE: Int = 0
  val WARM_TEMPERATURE: Int = 20
  val HOT_TEMPERATURE: Int = 35

object HumidityType extends Genotype:
  val LOW_HUMIDITY: Int = 10
  val MEDIUM_HUMIDITY: Int = 35
  val HIGH_HUMIDITY: Int = 70

object ColorType extends Genotype:
  val LIGHTGREEN: String = "lightgreen"
  val DARKGREEN: String = "darkgreen"
  val GREEN: String = "green"

import scala.util.Random

def getRandomGenotype[A <: Genotype](genotype: A): String =
  val fields = for {
    field <- genotype.getClass.getDeclaredFields.filter(!genotype.getClass.getFields.contains(_))
  } yield {
    field.setAccessible(true)
    field.get(genotype).toString
  }
  Random.shuffle(fields).head
