import it.unibo.pps.evoldino.model.createListDemoDinosaur
import it.unibo.pps.evoldino.model.dinosaur.{Dinosaur, Population}
import it.unibo.pps.evoldino.model.disaster.DisasterGenerator.{createListOfDisasters, createRandomDisasterWithProb}
import it.unibo.pps.evoldino.model.disaster.{AreaEffect, ClimateEffect, Disaster}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers

class DisasterDinosaur_2 extends AnyFunSpec:

  val populationInitial: Population = createListDemoDinosaur()
  val lf0: Double = populationInitial(0).lifepoints
  val lf1: Double = populationInitial(1).lifepoints
  val lf2: Double = populationInitial(2).lifepoints
  val lf3: Double = populationInitial(3).lifepoints
  val lf4: Double = populationInitial(4).lifepoints
  val lf5: Double = populationInitial(5).lifepoints

  describe("Tests for Earthquake") {
    it("population is changed Earthquake") {
      val populationDinoEarthquake = createListDemoDinosaur()
      val demoDisEarthquake: AreaEffect = Disaster.Earthquake(e = 10, c = (30, 70))
      println("damage dinosaur at position 2 and 3 and 4")
      println("LP 2: " + populationDinoEarthquake(2).lifepoints)
      println("LP 3: " + populationDinoEarthquake(3).lifepoints)
      println("LP 4: " + populationDinoEarthquake(4).lifepoints)
      val populationDisEarthquake = demoDisEarthquake.applyDisaster(populationDinoEarthquake)

      assertResult(true)(lf0.equals(populationDisEarthquake(0).lifepoints))
      assertResult(true)(lf1.equals(populationDisEarthquake(1).lifepoints))
      assertResult(false)(lf2.equals(populationDisEarthquake(2).lifepoints))
      assertResult(false)(lf3.equals(populationDisEarthquake(3).lifepoints))
      assertResult(false)(lf4.equals(populationDisEarthquake(4).lifepoints))
      assertResult(true)(lf5.equals(populationDisEarthquake(5).lifepoints))

      println("LP 2 WITH DAMAGE: " + populationDisEarthquake(2).lifepoints)
      println("LP 3 WITH DAMAGE: " + populationDisEarthquake(3).lifepoints)
      println("LP 4 WITH DAMAGE: " + populationDisEarthquake(4).lifepoints)

    }
  }

  describe("Tests for Meteorite") {
    it("population is changed Meteorite") {
      val populationDinoMeteorite = createListDemoDinosaur()
      val demoDisMeteorite: AreaEffect = Disaster.Meteorite(e = 0, c = (50, 50))
      println("damage dinosaur at position 5")
      println("LP 5: " + populationDinoMeteorite(5).lifepoints)
      val populationDisMeteorite = demoDisMeteorite.applyDisaster(populationDinoMeteorite)

      assertResult(true)(lf0 == populationDisMeteorite(0).lifepoints)
      assertResult(true)(lf1 == populationDisMeteorite(1).lifepoints)
      assertResult(true)(lf2 == populationDisMeteorite(2).lifepoints)
      assertResult(true)(lf3 == populationDisMeteorite(3).lifepoints)
      assertResult(true)(lf4 == populationDisMeteorite(4).lifepoints)
      assertResult(false)(lf5 == populationDisMeteorite(5).lifepoints)

      println("LP 5 WITH DAMAGE: " + populationDisMeteorite(5).lifepoints)
    }
  }

  describe("Tests for IceAge") {
    it("population is changed IceAge") {
      val populationDinoIceAge = createListDemoDinosaur()
      val demoDisIceAge: ClimateEffect = Disaster.IceAge
      println("damage population with IceAge")
      val populationDisIceAge = demoDisIceAge.applyDisaster(populationDinoIceAge)

      assertResult(false)(lf0 == populationDisIceAge(0).lifepoints)
      assertResult(false)(lf1 == populationDisIceAge(1).lifepoints)
      assertResult(false)(lf2 == populationDisIceAge(2).lifepoints)
      assertResult(false)(lf3 == populationDisIceAge(3).lifepoints)
      assertResult(false)(lf4 == populationDisIceAge(4).lifepoints)
      assertResult(false)(lf5 == populationDisIceAge(5).lifepoints)
    }
  }

  describe("Tests for Drought") {
    it("population is changed Drought") {
      val populationDinoDrought = createListDemoDinosaur()
      val demoDisDrought: ClimateEffect = Disaster.Drought
      print("damage population with Drought")
      val populationDisDrought = demoDisDrought.applyDisaster(populationDinoDrought)

      assertResult(false)(lf0 == populationDisDrought(0).lifepoints)
      assertResult(false)(lf1 == populationDisDrought(1).lifepoints)
      assertResult(false)(lf2 == populationDisDrought(2).lifepoints)
      assertResult(false)(lf3 == populationDisDrought(3).lifepoints)
      assertResult(false)(lf4 == populationDisDrought(4).lifepoints)
      assertResult(false)(lf5 == populationDisDrought(5).lifepoints)
    }
  }
