package omar.git.mirror.app


import grails.converters.JSON


class GitController {

	def gitService

	def index() {
		def json = request.JSON


		render gitService.mirror( json )
	}

	def mirrorRepos() {
		def response = []
		grailsApplication.config.repositories.each {
			response << gitService.mirror([
				repository: [
					name: it.key,
					ssh_url: it.value
				]
			])
		}


		render response as JSON
	}
}
