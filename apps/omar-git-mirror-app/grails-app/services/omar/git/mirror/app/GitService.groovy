package omar.git.mirror.app


import grails.transaction.Transactional


@Transactional
class GitService {

    def grailsApplication


    def mirror( json ) {
        def repository = json.repository

        def repoName = repository?.name
        def sshUrl = repository?.ssh_url

        if ( !repoName || !sshUrl ) {
            return "No repository name or SSH URL."
        }

        def repoDir = new File( "${ repoName }.git" )

        // if the repo directory doesn't exist
        if ( !repoDir.exists() ) {
            // clone the repo
            def cloneCommand = "git clone --mirror ${ sshUrl }"
            println cloneCommand
            def cloneProcess = cloneCommand.execute()
            cloneProcess.waitFor()

            // change the remote push url for the origin
            def setUrlCommand = "git remote set-url --push origin ${ grailsApplication.config.repoMirrorUrl }/${ repoName }.git"
            println setUrlCommand
            def setUrlProcess = setUrlCommand.execute( null, repoDir )
            setUrlProcess.waitFor()
        }

        // fetch updates
        def fetchCommand = "git fetch -p origin"
        println fetchCommand
        def fetchProcess = fetchCommand.execute( null, repoDir)
        fetchProcess.waitFor()

        // push updates
        def pushCommand = "git push --mirror"
        println pushCommand
        def pushProcess = pushCommand.execute( null, repoDir)
        pushProcess.waitFor()


        return json
    }
}
