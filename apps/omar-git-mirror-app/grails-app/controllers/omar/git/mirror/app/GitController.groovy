package omar.git.mirror.app


class GitController {

	def gitService

	def index() {
		def json = request.JSON


		render gitService.mirror( json )
	}
}
