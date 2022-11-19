import it.unibo.pps.evoldino.model.*
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers as MustMatchers
import it.unibo.pps.evoldino.model.Disaster
import it.unibo.pps.evoldino.model.Disaster.IceAge
import it.unibo.pps.evoldino.model.Disaster.Drought
import it.unibo.pps.evoldino.model.AreaEffect
import it.unibo.pps.evoldino.model.ClimateEffect
import scala.collection.mutable.ListBuffer

class testDemo2 extends AnyFunSpec:

  describe("Tests for apply Disaster") {
    val testDisasterSingleRandomProb = createSingleRandomDisasterProb()
    val population = createDemoDinosaur()

    it("should have elements") {
      print("\n DIS 1 \n")
      assertResult(false)(testDisasterSingleRandomProb.equals(()))
      println(testDisasterSingleRandomProb)
      println("\n OK DIS \n")
    }
    it("should have a population") {
      print("population")
      assertResult(false)(population.isEmpty)
    }
    it("population is changed") {

      val demoDisEarthquake: AreaEffect = Disaster.Earthquake(e = 10, c = (20, 60))
      val demoDisMeteorite: AreaEffect = Disaster.Meteorite(e = 0, c = (50, 50))
      val demoDisIceAge: ClimateEffect = Disaster.IceAge
      val demoDisDrought: ClimateEffect = Disaster.Drought

      val disList = new ListBuffer[Disaster]
      disList += demoDisEarthquake
      disList += demoDisMeteorite
      disList += demoDisIceAge
      disList += demoDisDrought
      val disasters = disList.toList

      print("changed population \n")

      val populationNow = applyDisaster(disasters,population)
      print(populationNow)
      print("changed population end \n")
      assertResult(false)(population.equals(populationNow))
    }
  }
