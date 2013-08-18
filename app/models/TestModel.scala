package models

import play.api.Logger

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB


case class TestModel(id: Option[Long] = None, name: String, value: Int)

object TestModel extends Table[TestModel]("testModel") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def value = column[Int]("value")

  //def users = foreignKey("fkUserPermissions_user", userId, Users)(_.uid)

  // Indexes
  def idxName = index("idxTestModel_Name", name, unique = true)

  def * = id.? ~ name ~ value <>(TestModel.apply _, TestModel.unapply _)

  def findSome(minVal: Int): List[TestModel] = {
    Logger.debug(s"TestModel.findSome($minVal)")

    DB.withSession {
      implicit session =>
        val q = for {
          test <- TestModel
          if test.value > minVal
        } yield test

        q.list()
    }
  }

}
