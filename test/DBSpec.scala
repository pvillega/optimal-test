/**
 *
 * User: pvillega
 */
package test

import org.specs2.mutable._

import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import play.api.test._
import play.api.test.Helpers._
import models._
import play.api.Logger
import play.api.test.FakeApplication

/**
 * test the kitty cat database
 */
class DBSpec extends Specification {

  "DB" should {
    "work as expected" in new WithApplication {


      DB.withSession{ implicit s:Session =>
        val someTest = Seq(
          TestModel(None, "A", 3),
          TestModel(None, "B", 5)
        )

        val testModel = someTest ++ Seq(
          TestModel(None, "C", -1)
        )

        TestModel.insertAll( testModel: _*)
        Query(TestModel).list.length must equalTo(testModel.length)

        TestModel.findSome(0).toList.length must equalTo(someTest.length)
      }
    }

    "select the correct testing db settings by default" in new WithApplication(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      DB.withSession{ implicit s:Session =>
        s.conn.getMetaData.getURL must startWith("jdbc:h2:mem:play-test")
      }
    }

    "use the default db settings when no other possible options are available" in new WithApplication {
      DB.withSession{ implicit s:Session =>
        s.conn.getMetaData.getURL must equalTo("jdbc:h2:mem:play")
      }
    }
  }

}
