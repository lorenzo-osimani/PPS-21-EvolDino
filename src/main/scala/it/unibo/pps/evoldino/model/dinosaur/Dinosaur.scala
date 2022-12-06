package it.unibo.pps.evoldino.model.dinosaur
import it.unibo.pps.evoldino.model.Disaster
import scala.util.Random
import scala.collection.mutable.ListBuffer

import it.unibo.pps.evoldino.controller.engine.EngineConstants

import scala.util.Random
import it.unibo.pps.evoldino.model.dinosaur.Gender
import it.unibo.pps.evoldino.model.dinosaur.gene.Gene
import it.unibo.pps.evoldino.model.world.WorldConstants
import it.unibo.pps.evoldino.utils.GlobalUtils.chooseBetweenTwo
import it.unibo.pps.evoldino.utils.PimpScala.given

sealed trait Gender
case object Male extends Gender
case object Female extends Gender

/** Represents a dinosaur */
<<<<<<< HEAD
trait Dinosaur:
  val genes: Gene
  val gender: Gender
  val mother: Option[Dinosaur]
  val father: Option[Dinosaur]
=======
 sealed trait Dinosaur:
  val kind: String // erbivoro o carnivoro
  val name: String // es.: t-rex,brontosaurus..
  val height: Int
  val weight: Int
  val color: String
  val gender: Gender
  val mum: Option[Dinosaur]
  val dad: Option[Dinosaur]

sealed trait Gender
case object male extends Gender
case object female extends Gender

sealed trait Kind
case object herbivorous extends Kind
case object carnivorous extends Kind
  def testAge: Int

  def testLifePoints: Int

  def testCoordinates: (Int, Int)

  def testDinoID: Int
>>>>>>> ecf226acd3d2f1a2d984da4a65d478c0245809cb

  /** @return the age of the dinosaur */
  def age: Int

<<<<<<< HEAD
<<<<<<< HEAD
  /** @return the lifepoints of the dinosaur */
  def lifepoints: Float
=======
  /** @return the life points of the dinosaur */
  def lifepoints: Double
>>>>>>> be4d217f645a9c6c7fb9f71479a1b75f47facfe0
=======
  /** Setter for age. */
  protected def age_=(age: Int): Unit
  private val MIN_AGE: Int = 0;
  private val MAX_AGE: Int = 40;
>>>>>>> ecf226acd3d2f1a2d984da4a65d478c0245809cb

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

<<<<<<< HEAD
  def kill(): Unit = throw IllegalStateException()

  /** Method that moves the dinosaur instance on the map */
  def moveDinosaur(): Unit = throw IllegalStateException()

=======
  /** @return the lifepoints of the dinosaur */
  def lifepoints: Int

  /** Setter for lifepoints. */
  protected def lifepoints_=(lifepoints: Int): Unit
  private val MAX_LIFEPOINTS: Int = 100;

  def damageDinosaur (damage: Int)  =
   lifepoints -= damage
    if(lifepoints <= 0)
    {this.kill();}

>>>>>>> ecf226acd3d2f1a2d984da4a65d478c0245809cb
  override def toString: String =
    super.toString +
      "\n genes: {" +
      "\n" + genes +
      "\n }" +
      "\n gender: " + gender +
      "\n age: " + age +
      "\n isAlive: " + isAlive

<<<<<<< HEAD
object Dinosaur {

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
        _coordinates._1 keepValueInCircularRange (delta_x, 100),
        _coordinates._2 keepValueInCircularRange (delta_y, 100)
      )
}

 case class ImmutableDinosaur(dinosaur: Dinosaur) extends Dinosaur:
  override val genes = dinosaur.genes
  override val gender = dinosaur.gender
  override val father = dinosaur.father
  override val mother = dinosaur.mother
  override val age = dinosaur.age
  override val lifepoints = dinosaur.lifepoints
  override val coordinates = dinosaur.coordinates

  override def isAlive: Boolean = true
=======
  /** Method that updates the dinosaur age for the next generation */
  def incrementAge(): Unit =
    { age += 1
  if (age >= MAX_AGE) this.kill()
    }
  /** Method that updates the dinosaur lifepoints for the next generation */
  def decrementLifepoints(): Unit =
  { lifepoints -= 1
    if (lifepoints <= 0) this.kill()
  }

object Dinosaur {

 def apply(
   kind: String,
   name: String,
   height: Int,
   weight: Int,
   color: String,
   gender: Gender,
   age: Int,
   testLifePoints: Int,
   testCoordinates: (Int, Int),
   testDinoID: Int): Dinosaur =

  new DinosaurImpl(
   kind,
   name,
   height,
   weight,
   color,
   gender,
   age,
   testLifePoints,
   testCoordinates,
   testDinoID
  )

 private class DinosaurImpl(
     override val kind: String,
     override val name: String,
     override val height: Int,
     override val weight: Int,
     override val color: String,
     override val gender: Gender,
     var age: Int,
     var testLifePoints: Int,
     var testCoordinates: (Int, Int),
     var testDinoID: Int)

   extends Dinosaur
}

/** Represents a Dinosaur that has just been created after reproduction. */
case class newDinosaur(
                       override val kind: randomKind,
                       override val name: String,
                       override val height: randomHeight,
                       override val weight: randomWeight,
                       override val color: String,
                       override val gender: randomGender,
                       override val mum: Option[Dinosaur],
                       override val dad: Option[Dinosaur]
                     ) extends Dinosaur {
  override var age: Int = 0
  override var lifepoints: Int = 100
  override var isAlive: Boolean = true
 }

val randomGender: () => Gender = () => Seq(male, female).random
val randomKind: () => Kind = () => Seq(herbivorous, carnivorous ).random
private val randomWeight: Int = Random.between(60000, 80000)
private val randomHeight: Int = Random.between(5,25)

 def deleteDeadDino (l: List[Dinosaur]): List[Dinosaur] =
  val ListDino = new ListBuffer[Dinosaur]
  for (ListDino <- l)
    if (lifepoints > 0)
    val newList = ListDino.toList
    print(newList)

  def genderDino (l: List[Dinosaur]): List[Dinosaur] =
   val maleDino = new ListBuffer[Dinosaur]
   val femaleDino = new ListBuffer[Dinosaur]
   for (maleDino <- l)
   if (Gender == male)
   print(maleDino)
   else if (Gender == female)
     print(femaleDino)
>>>>>>> ecf226acd3d2f1a2d984da4a65d478c0245809cb
