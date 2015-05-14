package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class User(firstName: String, lastName: String, userName: String, email: String, password: String)

object User {
	val user = {
		get[String]("firstName") ~
		get[String]("lastName") ~
		get[String]("userName") ~
		get[String]("email") ~
		get[String]("password") map {
			case firstName~lastName~userName~email~password => User(firstName, lastName, userName, email, password)
		} 
	}

	def authenticate(email: String, password: String): Option[User] = {
		DB.withConnection { implicit connection =>
			SQL(
				"select * from user where email = {email} and password = {password} "
				).on(
					'email -> email,
					'password -> password
				).as(User.user.singleOpt)
		}
	}

	def register(user: User) = {
		DB.withConnection { implicit connection => 
			SQL( "insert into user (firstname, lastname, username, email, password) values ({firstName},{lastName},{userName},{email},{password})").on(
					'firstName -> user.firstName,
					'lastName -> user.lastName,
					'userName -> user.userName,
					'email -> user.email,
					'password -> user.password
				).executeUpdate()
		}
	}
}