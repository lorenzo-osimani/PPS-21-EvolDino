package it.unibo.pps.evoldino.model.dinosaur

/** It's an enumeration that defines the names of all characteristics  */
private [model] object GeneClassification extends Enumeration {

  type GeneClassification = Value
  val TEMPERATURE_GENE, AGGRESSION_GENE, REPRODUCTION_GENE, LONGEVITY_GENE,
  GROWTH_GENE, SPEED_GENE = Value
}
