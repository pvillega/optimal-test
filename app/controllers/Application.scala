package controllers

import play.api._
import play.api.mvc._
import models.TestModel

import play.api.Play.current
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._

object Application extends Controller {
  
  def index = DBAction {  implicit rs  =>
    Ok(views.html.index("Your new application is ready."))
  }
  
}