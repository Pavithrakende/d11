pipeline {
    agent any

    environment {
        GIT_REPO_URL = 'https://github.com/Pavithrakende/d11.git'
        NGINX_PATH = 'C:\\Users\\pavit\\OneDrive\\Desktop\\jenkins,inginx\\nginx-1.24.0\\nginx-1.24.0\\htmldocs'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Use the checkout step to clone the Git repository
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git1', url: 'https://github.com/Pavithrakende/d11.git']]])
                }
            }
        }

        stage('Deploy to Nginx') {
            steps {
                script {
                    // Using the Jenkins workspace variable to reference files
                    bat 'xcopy /y "C:\\Users\\pavit\\OneDrive\\Desktop\\d1\\index.html" "%NGINX_PATH%"'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded! You can add additional steps here.'
        }
        failure {
            echo 'Pipeline failed! You may want to take some actions.'
        }
    }
}
