import play.api.{Application, GlobalSettings}

import models.TestModel
import play.api.Play.current
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._

/**
 *
 * User: pvillega
 */
object Global extends GlobalSettings {

  override def onStart(app: Application) {

    //init in memory db
    //    DB.withSession {
    //      implicit session:Session =>
    //        val model = TestModel.ddl
    //        model.create
    //    }

  }

}
