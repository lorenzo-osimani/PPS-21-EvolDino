package it.unibo.pps.evoldino.model.dinosaur
import it.unibo.pps.evoldino.model.Disaster

import scala.collection.mutable.ListBuffer

/** Represents a dinosaur */
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

  /** @return the age of the dinosaur */
  def age: Int

  /** Setter for age. */
  protected def age_=(age: Int): Unit
  private val MIN_AGE: Int = 0;
  private val MAX_AGE: Int = 40;

  /** Method to check if a dinosaur is alive.
   * @return true if the dinosaur is alive, false otherwise */
  def isAlive: Boolean

  /** Setter for alive. */
  protected def isAlive_=(isAlive: Boolean): Unit

  /** Kills the dinosaur by updating the alive value to false. */
  def kill(): Unit = isAlive = false

  /** @return the lifepoints of the dinosaur */
  def lifepoints: Int

  /** Setter for lifepoints. */
  protected def lifepoints_=(lifepoints: Int): Unit
  private val MAX_LIFEPOINTS: Int = 100;

  def damageDinosaur (damage: Int)  =
   lifepoints -= damage
    if(lifepoints <= 0)
    {this.kill();}

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
   kind:String,
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
                       override val name: ,
                       override val height:,
                       override val weight:,
                       override val color:,
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
