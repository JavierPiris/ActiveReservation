package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

import models.User

class ModelUserSpec extends Specification {
	
	"ModelUser" should {

		"Login user with email" in {
			running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
				val Some(user) = User.authenticate("jvrpiris@gmail.com", "tfg")
				user.email must equalTo("jvrpiris@gmail.com")
				user.password must equalTo("tfg")
			}
		}
	}
}