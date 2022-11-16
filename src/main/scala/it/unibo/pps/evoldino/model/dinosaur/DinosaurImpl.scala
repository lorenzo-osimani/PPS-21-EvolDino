package it.unibo.pps.evoldino.model.dinosaur

private class DinosaurImpl(dinosaur: Dinosaur) extends Dinosaur {

  override val kind: String = dinosaur.kind
  override val name: String = dinosaur.name
  override val height: Int = dinosaur.height
  override val weight: Int = dinosaur.weight
  override val color: String = dinosaur.color
  override val gender: String = dinosaur.gender
  override val age: Int = dinosaur.age
  override val isAlive: Boolean = dinosaur.isAlive

}
