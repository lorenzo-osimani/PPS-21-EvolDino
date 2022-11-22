package it.unibo.pps.evoldino.model.dinosaur
import it.unibo.pps.evoldino.model.Disaster

/** Represents a dinosaur */
 sealed trait Dinosaur:
  val kind: String // erbivoro o carnivoro
  val name: String // es.: t-rex,brontosaurus..
  val height: Int
  val weight: Int
  val color: String
  val gender: String

  def testAge: Int

  def testLifePoints: Int

  def testCoordinates: (Int, Int)

  def testDinoID: Int

  /** @return the age of the dinosaur */
  def age: Int

  /** Setter for age. */
  protected def age_=(age: Int): Unit
  private val MIN_AGE: Int = 1;
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

  def damageDinosaur (damage: Int) =
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
   gender: String,
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
     override val gender: String,
     var age: Int,
     var testLifePoints: Int,
     var testCoordinates: (Int, Int),
     var testDinoID: Int)

   extends Dinosaur
}
