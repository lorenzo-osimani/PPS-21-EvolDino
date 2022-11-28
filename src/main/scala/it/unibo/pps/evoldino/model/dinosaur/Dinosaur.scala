package it.unibo.pps.evoldino.model.dinosaur

import scala.util.Random
import it.unibo.pps.evoldino.model.dinosaur.Gender

sealed trait Gender
case object Male extends Gender
case object Female extends Gender

/** Represents a dinosaur */
trait Dinosaur:
  val kind: String
  val name: String
  val height: Int
  val weight: Int
  val color: String
  val gender: Gender
  val mother: Option[Dinosaur]
  val father: Option[Dinosaur]

  /** @return the age of the dinosaur */
  def age: Int

  /** @return the life points of the dinosaur */
  def lifepoints: Int

  /** @return the coordinates of the dinosaur */
  def coordinates: (Int, Int)

  /**
   * Method to check if a dinosaur is alive.
   * @return
   *   true if the dinosaur is alive, false otherwise
   */
  def isAlive: Boolean

  /** Method that updates the dinosaur instance for the next generation */
  def incrementAge(): Unit = ()

  /** Method that damages the dinosaur instance because of natural causes */
  def damageDinosaur(damage: Int): Unit = ()

  /** Method that moves the dinosaur instance on the map */
  def moveDinosaur(): Unit = ()

  override def toString: String =
    super.toString +
      "\n kind: " + kind +
      "\n name: " + name +
      "\n height: " + height +
      "\n weight: " + weight +
      "\n color: " + color +
      "\n gender: " + gender +
      "\n age: " + age +
      "\n isAlive: " + isAlive

object Dinosaur {

  def apply(kind: String,
  name: String,
  height: Int,
  weight: Int,
  color: String,
  gender: Gender,
  max_lifepoints: Int,
  starting_coordinates: (Int, Int),
  mother: Option[Dinosaur] = Option.empty,
  father: Option[Dinosaur] = Option.empty): Dinosaur =
    new DinosaurImpl(
    kind,
    name,
    height,
    weight,
    color,
    gender,
    max_lifepoints,
    starting_coordinates,
    mother,
    father
  )

  private class DinosaurImpl(
                              override val kind: String,
                              override val name: String,
                              override val height: Int,
                              override val weight: Int,
                              override val color: String,
                              override val gender: Gender,
                              max_lifepoints: Int,
                              starting_coordinates: (Int, Int),
                              override val mother: Option[Dinosaur],
                              override val father: Option[Dinosaur])
    extends Dinosaur :

    private var _age: Int = 0
    private var _lifepoints: Int = max_lifepoints
    private var _coordinates: (Int, Int) = starting_coordinates
    private var alive: Boolean = true

    override def isAlive: Boolean = alive

    private def kill(): Unit = alive = false

    override def age: Int = _age

    override def incrementAge(): Unit =
      _age += 1
      damageDinosaur(1)
      if (age >= 100) kill()

    override def lifepoints: Int = _lifepoints

    override def damageDinosaur(damage: Int): Unit =
      _lifepoints -= damage
      if (lifepoints <= 0) kill()

    override def coordinates: (Int, Int) = _coordinates

    import it.unibo.pps.evoldino.utils.PimpScala.given

    override def moveDinosaur(): Unit =
      val delta_x = Random.between(-1, 2)
      val delta_y = Random.between(-1, 2)
      _coordinates = (
        _coordinates._1 keepValueInBounds(delta_x, 100),
        _coordinates._2 keepValueInBounds(delta_y, 100)
      )
}

case class ImmutableDinosaur(dinosaur: Dinosaur) extends Dinosaur:
  override val kind = dinosaur.kind
  override val name = dinosaur.kind
  override val height = dinosaur.height
  override val weight = dinosaur.weight
  override val color = dinosaur.color
  override val gender = dinosaur.gender
  override val father = dinosaur.father
  override val mother = dinosaur.mother
  override val age = dinosaur.age
  override val lifepoints = dinosaur.lifepoints
  override val coordinates = dinosaur.coordinates

  override def isAlive: Boolean = true
