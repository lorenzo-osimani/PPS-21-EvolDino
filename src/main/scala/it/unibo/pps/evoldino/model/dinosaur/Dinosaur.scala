package it.unibo.pps.evoldino.model.dinosaur

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
  private val MAX_AGE: Int = 100;

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
   // lifepoints -= damage
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

  /** Method that updates the dinosaur instance for the next generation */
  def incrementAge(): Unit = age += 1
  if (age >= MAX_AGE) isAlive = false
  
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