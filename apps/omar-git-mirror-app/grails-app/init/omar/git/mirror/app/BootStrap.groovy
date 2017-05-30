package omar.git.mirror.app

class BootStrap {

    def grailsApplication


    def init = {
        servletContext ->

        def sshDirectory = new File( "${ System.getenv( "HOME" ) }/.ssh" )
        if ( !sshDirectory.exists() ) { sshDirectory.mkdir() }

        def id_rsa = new File( "${ sshDirectory }/id_rsa" )
        if ( !id_rsa.exists() ) {
            id_rsa.write( grailsApplication.config.sshKey )
            "chmod 600 ${ id_rsa }".execute()
        }

        def id_rsa_pub = new File( "${ sshDirectory }/id_rsa.pub" )
        if ( !id_rsa_pub.exists() ) {
            id_rsa_pub.write( grailsApplication.config.sshKeyPublic )
            "chmod 644 ${ id_rsa_pub }".execute()
        }

        def config = new File( "${ sshDirectory }/config" )
        if ( !config.exists() ) {
            grailsApplication.config.knownHosts.each {
                config.append( "Host ${ it }\n" )
                config.append( "StrictHostKeyChecking no\n" )
            }
        }
    }
    def destroy = {
    }
}
