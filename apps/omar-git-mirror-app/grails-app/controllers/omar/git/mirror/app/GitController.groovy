package omar.git.mirror.app


class GitController {

	def gitService

	def index() {
		def json = request.JSON

render "test"
//		render gitService.mirror( json )
	}
}
