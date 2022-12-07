package it.unibo.pps.evoldino.model.dinosaur.gene

import it.unibo.pps.evoldino.model.dinosaur.gene.Genotype
import scala.util.Random

sealed trait Genotype

  object LifeSpan extends Genotype
  val SHORT_LIFE: Int = 50
  val MEDIUM_LIFE: Int = 75
  val LONG_LIFE: Int = 100

  object TemperatureType extends Genotype
  val COLD_TEMPERATURE: Int = 0
  val WARM_TEMPERATURE: Int = 20
  val HOT_TEMPERATURE: Int = 35

  object HumidityType extends Genotype
  val LOW_HUMIDITY: Int = 10
  val MEDIUM_HUMIDITY: Int = 35
  val HIGH_HUMIDITY: Int = 70

def getRandomGenotype[A <: Genotype](genotype: A): Int =
  val fields = for {
    field <- genotype.getClass.getDeclaredFields.filter(!genotype.getClass.getFields.contains(_))
  } yield {
    field.setAccessible(true)
    field.getInt(genotype)
  }
  Random.shuffle(fields).head
