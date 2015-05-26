package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import anorm._

import views._
import models.User

object Users extends Controller {
	val loginForm = Form (
		tuple (
			"email" -> text,
			"password" -> text
			).verifying("Invalid email or password", result => result match{
				case (email, password) => User.authenticate(email, password).isDefined
				}) 
	)
}