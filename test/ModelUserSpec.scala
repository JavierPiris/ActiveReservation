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

		"Register user Rocio Gomez" in {
			running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
				User.register(User("rocio", "gomez", "rgomez", "rg@gmail.com", "test"))
				val Some(user) = User.authenticate("rg@gmail.com", "test")
				user.email must equalTo("rg@gmail.com")
				user.password must equalTo("test")
			}
		}

		"Find user with username jvrpiris" in {
			running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
				val Some(user) = User.findByUsername("jvrpiris")
				user.userName must equalTo("jvrpiris")
			}
		}
	}
}