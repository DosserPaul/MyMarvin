folder('/Tools') {
    displayName('Tools')
    description('Folder for miscellaneous tools.')
}
freeStyleJob('/Tools/clone-repository') {
    steps {
        shell('git clone $GIT_REPOSITORY_URL')
    }
    wrappers {
        preBuildCleanup()
    }
    parameters {
        stringParam("GIT_REPOSITORY_URL", "", "Git URL of the repository to clone")
    }
}
freeStyleJob('/Tools/SEED') {
    parameters {
        stringParam("DISPLAY_NAME", "", "Display name for the job")
        stringParam("GITHUB_NAME", "", 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
    }
    steps {
        dsl {
            text('''freeStyleJob("$DISPLAY_NAME") {
    steps {
        shell("make fclean")
        shell("make")
        shell("make tests_run")
        shell("make clean")
    }
    scm {
        github("$GITHUB_NAME")
    }
    triggers {
        pollSCM {
            scmpoll_spec("* * * * *")
        }
    }
    wrappers {
        preBuildCleanup()
    }
}''')
        }
    }
}
