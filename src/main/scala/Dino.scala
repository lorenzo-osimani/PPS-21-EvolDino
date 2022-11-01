class Dino {

  /** Represents a Dino */
  sealed trait Dino {
    val type: String
    val name: String
    val height: Int
    val weight: Int
    val color: String
    val gender: String

    /** @return the age of the bunny */
    def age: Int

    /** Method to check if a bee is alive.
     *
     * @return true if the Dino is alive, false otherwise */
    def isAlive: Boolean

    /** Setter for age. */
    protected def age_=(age: Int): Unit

    /** Setter for alive. */
    protected def alive_=(alive: Boolean): Unit

    override def toString: String = {
      super.toString +
        "\n type: " + type +
        "\n name: " + name +
        "\n height: " + height +
        "\n weight: " + weight +
        "\n color: " + color +
        "\n gender: " + gender +
        "\n isAlive: " + isAlive +
        "\n age: " + age +
    }
  }
}
