class Disaster:

  /** Represent a Disaster */
    sealed trait Disaster:
    //forse meglio mettere var??
      def damage: Int
      def extension: Int
      def probability: Int
      def temperature: Option[Double]
      //val coordinates: Tuple2[_: Int,_: Int]
      val coordinates: (Int, Int) = Option(x,y)
       //def coordinates: Tuple2[x,y] => (x: Int, y: Int)

      //diffrenza that val e deff , def sembrano valori che possono ritornare invece val valori

      //meglio mettere tutto protected o private o caosa ? oppure dato che è un salead trait con ovveride è a posto
      //meglio usare le apply??o in aggiunta
      //meglio tipato o non tipato
      
      case class Earthquake extends Disaster:
        override val damage: Int = 1000
        override val extension: Int = 3
        //nonnn sarebbe meglio che l engine decidesse le coordinate
        //override coordinates: engine probabilmente
        override val probability: Double = 0.5

      case class Meteorite extends Disaster:
        override def gravity: Int = 1
        override def damage: Int = 1000
        override def extension: Int = 3
        override def probability: Double = 0.5

//refactoring
      case class IceAge extends Disaster:
        override val gravity: Int = 1
        override val damage: Int = 1000
        override val extension: Int = 3
        override def probability: Double = 0.5
        override def temperature: Int = 100000

      case class Drought extends Disaster:
        override def gravity: Int = 11
        override def damage: Int = 100
        override def extension: Int = 23
        override def probability: Double = 0.3
        //errore scompare ma non ha senso, prova a togliere Option qui
        override def temperature: Option[Double] = 10000
        override def interested: String = "Erbivorous"

 /* object LessAnimalsToEat extends Disaster:
        override def gravity: Int = 1
        override def damage: Int = 1000
        override def extension: Int = 3
        override def probability: Double = 0.5
        override def temperature: Double = 100000
        override def interested: String = "Erbivorous"

      object LessPlantsToEat extends Disaster :
         override def gravity: Int = 1
         override def damage: Int = 1000
         override def extension: Int = 3
         override def probability: Double = 0.5
         //errore scompare ma non ha senso, prova a togliere Option qui
         override def temperature: Option[Double] = 100000
         override def interested: String = "Erbivorous"*/

      override def toString: String = {
        super.toString +
          "\n damage: " + damage +
          "\n extension " + extension +
          "\n coordinateX" + coordinates(x)
          "\n coordinateY" + coordinates(y)
      }
         //cè un modo di creare un array per riprendere i danni creati
         //problema che aclune variabili devono stare entro un certo range
         //è posbbile settare questo range da pruma nella definizione delle def
      
      def createIceAge:
          IceAge
    
      def DoARandomDisaster: () => Disaster:
       random match
         case IceAge
         case EarthQuake
         case _ => throw Error
        

      //DOBBIAMO definire bene cosa vaqui e cosa nell engine
