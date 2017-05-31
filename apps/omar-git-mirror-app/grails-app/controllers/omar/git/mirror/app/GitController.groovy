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
				name: it.name,
				ssh_url: it.sshUrl
			])
		}


		render response as JSON
	}
}
