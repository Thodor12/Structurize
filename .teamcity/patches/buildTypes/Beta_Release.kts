package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Beta_Release'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Beta_Release")) {
    vcs {

        check(branchFilter == "+:*") {
            "Unexpected option value: branchFilter = $branchFilter"
        }
        branchFilter = "+:*"
    }

    expectSteps {
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build createChangelog curseforge publish"
            buildFile = "build.gradle"
            enableStacktrace = true
            dockerImage = "gradle:%env.GRADLE_VERSION%-%env.JDK_VERSION%"
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerRunParameters = """
                -v /opt/buildagent/gradle/caches:/home/gradle/.gradle/caches
                -u 0
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "%Project.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "2")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "libraries")
        }
    }
    steps {
        update<GradleBuildStep>(0) {
            id = "RUNNER_9"
            clearConditions()
            dockerImage = ""
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "")
        }
        check(stepsOrder == arrayListOf("RUNNER_85", "RUNNER_9")) {
            "Unexpected build steps order: $stepsOrder"
        }
        stepsOrder = arrayListOf("RUNNER_9")
    }
}
