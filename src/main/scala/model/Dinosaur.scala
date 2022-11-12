package scala.model

/** Represents a dinosaur */
sealed trait Dinosaur:
  val kind: String // erbivoro o carnivoro
  val name: String // es.: t-rex,brontosaurus..
  val height: Int
  val weight: Int
  val color: String
  val gender: String

  /** @return the age of the dinosaur */
  def age: Int

  /** Setter for age. */
  protected def age_=(age: Int): Unit

  /**
   * Method to check if a dinosaur is alive.
   *
   * @return
   *   true if the dinosaur is alive, false otherwise
   */
  def isAlive: Boolean

  /** Setter for alive. */
  protected def isAlive_=(isAlive: Boolean): Unit

  /** Kills the dinosaur by updating the alive value to false. */
  def kill(): Unit = isAlive = false

  override def toString: String =
    super.toString +
      "\n kind: " + kind +
      "\n name: " + name +
      "\n height: " + height +
      "\n weight: " + weight +
      "\n color: " + color +
      "\n gender: " + gender +
      "\n isAlive: " + isAlive +
      "\n age: " + age

  /** Method that updates the dinosaur instance for the next generation */
  def increaseAge(): Unit =
    age += 1
    //if (age >= MAX_AGE) isAlive = false
