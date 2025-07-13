@Library("my-shared-library")_

pipeline {
    agent any

    stages{ 

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
                script{
                    def projectKey = 'reactjs-devops8-template' 
                    def projectName = 'ReactjsDevOps8template'
                    def projectVersion = '1.0.0'
                    scanReactSonarqubeV2("${projectKey}","${projectName}","${projectVersion}")
                }
            }
        }


        // wait for the quality gate 
        stage("Wait for Quality Gate "){
            steps{
                script{
                   def qg = waitForQualityGate()
                    if ( qg.status != 'OK'){
                        sh """
                        echo " No need to build since you QG is failed "
                        """
                        currentBuild.result='FAILURE'
                        return 
                    }else {
                        echo "Quality of code is okay!! "
                        currentBuild.result='SUCCESS'
                    }
                }

            }
        }

        stage("Build"){
            when {
                expression { 
                    currentBuild.result == 'SUCCESS'
                }
            }
            steps{
                 sh """
                    docker build -t lyvanna544/jenkins-react-sonarqube-pipeline:${env.BUILD_NUMBER} . 
                """
            }
        }

        stage("Push"){
            when {
                expression { 
                    currentBuild.result == 'SUCCESS'
                }
            }
            steps{
               script{
                def dockerImage="lyvanna544/jenkins-react-sonarqube-pipeline:${env.BUILD_NUMBER}"
                 pushDockerToDH(dockerImage)
               }
            }
        }
    }
// post actions or post scripts  

    post{
        success{
                script{  
                    
                    def successMessage="""
                            Deployment is Success 👍
                            🟢 Access Service: https://sonarqube.devnerd.store            
                            📥  Job Name: ${env.JOB_NAME}
                            🎱   Build Number: ${env.BUILD_NUMBER}
                            """ 
                    sendTelegramMessageV2(successMessage)
                }
            
        }

        failure {
            script{ 
                
                def errorMessage="""
                            Deployment result in failures 🔥
                            🟢 Access Report: https://sonarqube.devnerd.store/dashboard?id=reactjs-devops8-template           
                            📥  Job Name: ${env.JOB_NAME}
                            🎱   Build Number: ${env.BUILD_NUMBER}
                            """ 
                sendTelegramMessageV2(errorMessage)      
                }
        }
        always {
            // built-in function used to clean the workspace of jenkins 
            echo "Clearing the workspace of ${env.JOB_NAME} "
            cleanWs()
        }
    }
}


