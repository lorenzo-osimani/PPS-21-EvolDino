import it.unibo.pps.evoldino.model.*
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.disaster.Disaster
import it.unibo.pps.evoldino.model.dinosaur.Dinosaur
import it.unibo.pps.evoldino.model.disaster.{ AreaEffect, ClimateEffect }

import scala.collection.mutable.ListBuffer

class testDemo2 extends AnyFunSpec:

  describe("Tests for apply Disaster") {
    val populationInitial = createDemoDinosaur()
    val lf0 = populationInitial(0).testLifePoints
    val lf1 = populationInitial(1).testLifePoints
    val lf2 = populationInitial(2).testLifePoints
    val lf3 = populationInitial(3).testLifePoints
    val lf4 = populationInitial(4).testLifePoints
    val lf5 = populationInitial(5).testLifePoints

    it("population is changed Earthquake") {

      val populationDinoEarthquake = createDemoDinosaur()
      val demoDisEarthquake: AreaEffect = Disaster.Earthquake(e = 10, c = (20, 60))
      print("lifepoints iniziali \n")
      print("colpire dinosauro 3 e 4 \n")
      print("LP 3: " + populationDinoEarthquake(3).testLifePoints + "\n")
      print("LP 4: " + populationDinoEarthquake(4).testLifePoints + "\n")
      val populationDisEarthquake = demoDisEarthquake.applyDisasterNEW(populationDinoEarthquake)
      print("LP 3: " + populationDisEarthquake(3).testLifePoints + "\n")
      print("LP 4: " + populationDisEarthquake(4).testLifePoints + "\n")
      print("changed population end \n")

      assertResult(true)(lf0.equals(populationDisEarthquake(0).testLifePoints))
      assertResult(true)(lf1.equals(populationDisEarthquake(1).testLifePoints))
      assertResult(true)(lf2.equals(populationDisEarthquake(2).testLifePoints))
      assertResult(false)(lf3.equals(populationDisEarthquake(3).testLifePoints))
      assertResult(false)(lf4.equals(populationDisEarthquake(4).testLifePoints))
      assertResult(true)(lf5.equals(populationDisEarthquake(5).testLifePoints))

    }
    it("population is changed Meteorite") {
      val populationDinoMeteorite = createDemoDinosaur()
      val demoDisMeteorite: AreaEffect = Disaster.Meteorite(e = 0, c = (50, 50))

      print("lifepoints iniziali \n")
      print("colpire dinosauro 5 \n")
      print("LP 5: " + populationDinoMeteorite(5).testLifePoints + "\n")
      val populationDisMeteorite = demoDisMeteorite.applyDisasterNEW(populationDinoMeteorite)
      print("LP 5: " + populationDisMeteorite(5).testLifePoints + "\n")
      print("changed population end \n")

      assertResult(true)(lf0 == populationDisMeteorite(0).testLifePoints)
      assertResult(true)(lf1 == populationDisMeteorite(1).testLifePoints)
      assertResult(true)(lf2 == populationDisMeteorite(2).testLifePoints)
      assertResult(true)(lf3 == populationDisMeteorite(3).testLifePoints)
      assertResult(true)(lf4 == populationDisMeteorite(4).testLifePoints)
      assertResult(false)(lf5 == populationDisMeteorite(5).testLifePoints)

    }
    it("population is changed IceAge") {
      val populationDinoIceAge = createDemoDinosaur()
      val demoDisIceAge: ClimateEffect = Disaster.IceAge
      print("change population with IceAge \n")
      val populationDisIceAge = demoDisIceAge.applyDisasterNEW(populationDinoIceAge)
      print("changed population end \n")

      assertResult(false)(lf0 == populationDisIceAge(0).testLifePoints)
      assertResult(false)(lf1 == populationDisIceAge(1).testLifePoints)
      assertResult(false)(lf2 == populationDisIceAge(2).testLifePoints)
      assertResult(false)(lf3 == populationDisIceAge(3).testLifePoints)
      assertResult(false)(lf4 == populationDisIceAge(4).testLifePoints)
      assertResult(false)(lf5 == populationDisIceAge(5).testLifePoints)

    }
    it("population is changed Drought") {
      val populationDinoDrought = createDemoDinosaur()
      val demoDisDrought: ClimateEffect = Disaster.Drought
      print("change population with Drought \n")
      val populationDisDrought = demoDisDrought.applyDisasterNEW(populationDinoDrought)
      print("changed population end \n")

      assertResult(false)(lf0 == populationDisDrought(0).testLifePoints)
      assertResult(false)(lf1 == populationDisDrought(1).testLifePoints)
      assertResult(false)(lf2 == populationDisDrought(2).testLifePoints)
      assertResult(false)(lf3 == populationDisDrought(3).testLifePoints)
      assertResult(false)(lf4 == populationDisDrought(4).testLifePoints)
      assertResult(false)(lf5 == populationDisDrought(5).testLifePoints)

    }
  }
