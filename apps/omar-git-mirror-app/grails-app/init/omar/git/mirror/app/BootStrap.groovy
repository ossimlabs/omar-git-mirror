package omar.git.mirror.app

class BootStrap {

    def grailsApplication


    def init = {
        servletContext ->

        def sshDirectory = new File( "${ System.getenv( "HOME" ) }/.ssh" )
        if ( !sshDirectory.exists() ) { sshDirectory.mkdir() }

        def id_rsa = new File( "${ sshDirectory }/id_rsa" )
        if ( !id_rsa.exists() ) {
//            id_rsa.write( grailsApplication.config.sshKey )
        }

        def id_rsa_pub = new File( "${ sshDirectory }/id_rsa.pub" )
        if ( !id_rsa_pub.exists() ) {
//            id_rsa_pub.write( grailsApplication.config.sshKeyPublic )
        }
    }
    def destroy = {
    }
}
