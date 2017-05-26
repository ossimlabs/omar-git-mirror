package omar.git.mirror.app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" {
        	action = "index"
			controller = "git"
		}
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
