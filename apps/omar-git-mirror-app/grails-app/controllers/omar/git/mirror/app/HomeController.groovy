package omar.git.mirror.app


class HomeController {

	def index() {
		def json = request.JSON
		println json


		render( view: "/index.gsp" )
	}
}
