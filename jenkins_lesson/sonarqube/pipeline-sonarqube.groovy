pipeline {
    agent any

    stages {
        stage('Clone ReactJs Code ') {
            steps {
                  git 'https://github.com/keoKAY/reactjs-devop8-template'

            }
        }


        stage("Check Code Quality in Sonarqube "){
            
            environment {
                scannerHome= tool 'sonarqube-scanner' 
            }

            steps{
                withSonarQubeEnv(credentialsId: 'SONARQUBE_TOKEN', installationName: 'sonarqube-scanner') {
                script{
                
                    def projectKey = 'reactjs-devops8-template' 
                    def projectName = 'ReactjsDevOps8template'
                    def projectVersion = '1.0.0' 
                    sh """
                    ${scannerHome}/bin/sonar-scanner \
                    -Dsonar.projectKey=${projectKey} \
                    -Dsonar.projectName="${projectName}" \
                    -Dsonar.projectVersion=${projectVersion} \
                     """   
                        
                        }
            }

            }
        }

        stage("Build"){
            steps{
                echo "Building the docker image "
            }
        }

        stage("Push"){
            steps{
                echo "Pushing the docker image to registry "
            }
        }
        

    }
}
