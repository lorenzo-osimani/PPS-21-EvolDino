package scala.model.dinosaur.GeneClassification

private [model] object GeneClassification extends Enumeration {

  type GeneClassification = Value
  val TEMPERATURE_GENE, AGGRESSION_GENE, REPRODUCTION_GENE, LONGEVITY_GENE,
  GROWTH_GENE, SPEED_GENE = Value
}
