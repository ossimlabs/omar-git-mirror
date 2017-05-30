package omar.git.mirror.app


import grails.transaction.Transactional


@Transactional
class GitService {

    def grailsApplication


    def mirror( json ) {
        def cloneUrl = json.repository?.clone_url
        def repoName = json.repository?.name

        if ( !cloneUrl || !repoName ) {
            return "No clone URL or repository name."
        }

        def repoDir = new File( "${ repoName }.git" )

        // if the repo directory doesn't exist
        if ( !repoDir.exists() ) {
            // clone the repo
            def cloneCommand = "git clone --mirror ${ cloneUrl }"
            println cloneCommand
            def cloneProcess = cloneCommand.execute()
            cloneProcess.waitFor()

            // change the remote push url for the origin
            def setUrlCommand = "git set-url --push origin ${ grailsApplication.config.repoMirrorUrl }/${ repoName }.git"
            println setUrlCommand
            def setUrlProcess = setUrlCommand.execute( null, repoDir )
            setUrlProcess.waitFor()
        }

        // fetch updates
        def fetchCommand = "git fetch -p origin"
        println fetchCommand
        def fetchProcess = fetchCommand.execute( null, repoDir)
        fetchCommand.waitFor()

        // push updates
        def pushCommand = "git push --mirror"
        println pushCommand
        def pushProcess = pushCommand.execute( null, repoDir)
        pushProcess.waitFor()


        return json
    }
}
