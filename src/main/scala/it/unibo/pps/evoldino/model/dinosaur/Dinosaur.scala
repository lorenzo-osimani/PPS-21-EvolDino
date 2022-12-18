package it.unibo.pps.evoldino.model.dinosaur

import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.model.dinosaur.Gender
import it.unibo.pps.evoldino.model.dinosaur.gene.Gene
import it.unibo.pps.evoldino.model.world.WorldConstants
import it.unibo.pps.evoldino.utils.GlobalUtils.chooseBetweenTwo
import it.unibo.pps.evoldino.utils.PimpScala.given
import scala.util.Random

sealed trait Gender
case object Male extends Gender
case object Female extends Gender

/** Represents a dinosaur */
trait Dinosaur:
  val genes: Gene
  val gender: Gender
  val mother: Option[Dinosaur]
  val father: Option[Dinosaur]

  /** @return the age of the dinosaur */
  def age: Int

  /** @return the life points of the dinosaur */
  def lifepoints: Double

  /** @return the coordinates of the dinosaur */
  def coordinates: (Int, Int)

  /**
   * Method to check if a dinosaur is alive.
   * @return true if the dinosaur is alive, false otherwise
   */
  def isAlive: Boolean

  /** Method that updates the dinosaur instance for the next generation */
  def incrementAge(): Unit = throw IllegalStateException()

  /** Method that damages the dinosaur instance because of natural causes */
  def damageDinosaur(damage: Double): Unit = throw IllegalStateException()

  def kill(): Unit = throw IllegalStateException()

  /** Method that moves the dinosaur instance on the map */
  def moveDinosaur(): Unit = throw IllegalStateException()

  override def toString: String =
    super.toString +
      "\n genes: {" +
      "\n" + genes +
      "\n }" +
      "\n gender: " + gender +
      "\n age: " + age +
      "\n isAlive: " + isAlive

object Dinosaur:

  def apply(
      genes: Gene,
      gender: Gender,
      starting_coordinates: (Int, Int) = (
        Random.nextInt(WorldConstants.dim_w_world + 1),
        Random.nextInt(WorldConstants.dim_h_world + 1)
      ),
      mother: Option[Dinosaur] = Option.empty,
      father: Option[Dinosaur] = Option.empty): Dinosaur =
    new DinosaurImpl(genes, gender, starting_coordinates, mother, father)

  def randomizedDinosaur() = Dinosaur(Gene.randomizedGene(), chooseBetweenTwo(Male, Female))

  private class DinosaurImpl(
                              override val genes: Gene,
                              override val gender: Gender,
                              starting_coordinates: (Int, Int),
                              override val mother: Option[Dinosaur],
                              override val father: Option[Dinosaur])
    extends Dinosaur:

    private var _age: Int = 0
    private var _lifepoints: Double = genes.lifespan.toFloat
    private var _coordinates: (Int, Int) = starting_coordinates
    private var alive: Boolean = true

    override def isAlive: Boolean = alive

    override def kill(): Unit = alive = false

    override def age: Int = _age

    override def incrementAge(): Unit =
      _age += 1
      damageDinosaur(1)

    override def lifepoints: Double = _lifepoints

    override def damageDinosaur(damage: Double): Unit =
      _lifepoints -= damage
      if (lifepoints <= 0) kill()

    override def coordinates: (Int, Int) = _coordinates

    override def moveDinosaur(): Unit =
      val delta_x = Random.between(-1, 2)
      val delta_y = Random.between(-1, 2)
      _coordinates = (
        _coordinates._1 keepValueInCircularRange (delta_x, WorldConstants.dim_w_world),
        _coordinates._2 keepValueInCircularRange (delta_y, WorldConstants.dim_h_world)
      )


/** An immutable version of a Dinosaur*/
case class ImmutableDinosaur(dinosaur: Dinosaur) extends Dinosaur:
  override val genes = dinosaur.genes
  override val gender = dinosaur.gender
  override val father = dinosaur.father
  override val mother = dinosaur.mother
  override val age = dinosaur.age
  override val lifepoints = dinosaur.lifepoints
  override val coordinates = dinosaur.coordinates

  override def isAlive: Boolean = true
