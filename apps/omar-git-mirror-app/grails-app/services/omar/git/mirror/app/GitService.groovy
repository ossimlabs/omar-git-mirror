package omar.git.mirror.app


import grails.transaction.Transactional


@Transactional
class GitService {

    def grailsApplication


    def mirror( json ) {
        def cloneUrl = json.repository?.clone_url
        def repoName = json.repository?.name

        if ( !repoName || !cloneUrl) {
            return "No repository name or clone URL."
        }
        if ( grailsApplication.config.repos[ repoName ].cloneUrl != cloneUrl ) {
            return "${ repoName } is not one of the repositories being mirrored."
        }

        def repoDir = new File( "${ repoName }.git" )

        // if the repo directory doesn't exists
        if ( !repoDir.exists() ) {
            // clone the repo
            def cloneCommand = "git clone --mirror ${ repository.clone_url }"
            def cloneProcess = cloneCommand.execute()
            cloneProcess.waitFor()

            // change the remote push url for the origin
            //def setUrlCommand = "git set-url --push origin ${ mirrorRepoUrl }/${ repoName }.git"
            //def setUrlProcess = setUrlCommand.execute( null, repoDir )
            //setUrlProcess.waitFor()
        }

        // fetch updates
        //def fetchCommand = "git fetch -p origin"
        //def fetchProcess = fetchCommand.execute( null, repoDir)
        //fetchCommand.waitFor()

        // push updates
        //def pushCommand = "git push --mirror"
        //def pushProcess = pushCommand.execute(
        //pushProcess.waitFor())


        return json

    }
}
