# omar-git-mirror
This repo is dedicated to storing the code necessary to mirror any repository on Github with DI2E's BitBucket. However, the idea can be modified to mirror any repository anywhere. 

## Setup
These steps are needed when setting up omar-git-mirror from scratch. They only need to be performed once.
1. [Generate an SSH key](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/)
2. Add the public and private keys to the `application.yml`. The `omar-git-mirror` service will read these from the config and place them into $HOME/.ssh, making the appropriate permission changes.
3. Add the repository host names to the `application.yml`. The `omar-git-mirror` service will read these from the config and place them into $HOME/.ssh/config to bypass the known host prompt during SSH communication.
4. Add the public key to the DI2E BitBucket Project.
5. Add the public key to a GttHub user's profile that has access to all the desired repositories.

## Mirroring a new repository
These steps are needed when a new repository is to be mirrored.
1. Create a new repository in DI2E's BitBucket with the same name as the one to be mirrored.
2. Ensure that the GitHub user with the correct access key (see section above) has access to the repository.
3. Add the following webhook to the GitHub repository.
    URL: http://omar-rel.ossim.io/omar-git-mirror
    Content Type: application/json

## API
Although a list of repositories is not necessary in the configuration to mirror a repository, the `/git/mirrorRepos` endpoint will cycle through any repositories listed in the `application.yml` file and execute the mirror service on each of them.
