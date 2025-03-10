pipeline {
    agent {
        docker{
            image "3.9.9-eclipse-temurin-11"
            args "entrypoint=''"
        }
    }

    parameters {
        string(name: 'TAG', defaultValue: 'run_login_tests_chrome', description: 'Tag to execute')
    }

    stages {

        stage('Compile project') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Grant bash files permission') {
            steps {
                sh "chmod +x batch/*.sh"
            }
        }

        stage('Execute tests') {
            steps {
                sh "batch/${params.TAG}.sh"
            }
        }
    }
    post {
        always {
            cucumber buildStatus: 'UNSTABLE',
            failedFeaturesNumber: 1,
            failedScenariosNumber: 1,
            skippedStepsNumber: 1,
            failedStepsNumber: 1,
            classifications: [
                    [key: 'Commit', value: '<a href="${GERRIT_CHANGE_URL}">${GERRIT_PATCHSET_REVISION}</a>'],
                    [key: 'Submitter', value: '${GERRIT_PATCHSET_UPLOADER_NAME}']
            ],
            reportTitle: 'My report',
            fileIncludePattern: 'reports/*.json',
            sortingMethod: 'ALPHABETICAL',
            trendsLimit: 100
         }
    }
}