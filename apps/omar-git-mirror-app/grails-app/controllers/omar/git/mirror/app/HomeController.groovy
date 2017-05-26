package omar.git.mirror.app


class HomeController {

	def grailsApplication


	def index() { println params
		def json = request.JSON
		println json


		render "Done"
	}
}
