pipeline {
    agent any

    stages {
        //  stage("Telegram Message"){
        //     steps{
        //         script{
        //             withCredentials([usernamePassword(credentialsId: 'TELEGRAM_BOT',
        //             passwordVariable: 'TOKEN', usernameVariable: 'CHAT_ID')]) {

        //                 script{
        //                     sendTelegramMessage("Good Pipeline ",
        //                      ${TOKEN},${CHAT_ID})
        //                 }
                        
        //             }     
        //         }
        //     }
        // }

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
                echo "Building the docker image "
            }
        }

        stage("Push"){
            when {
                expression { 
                    currentBuild.result == 'SUCCESS'
                }
            }
            steps{
                echo "Pushing the docker image to registry "
            }
        }
        

    }

    post{
        success{
                script{
                    withCredentials([usernamePassword(credentialsId: 'TELEGRAM_BOT',
                    passwordVariable: 'TOKEN', usernameVariable: 'CHAT_ID')]) {
                            
                            sendTelegramMessage("Deployment is success! ",
                             "${TOKEN}","${CHAT_ID}")
                        
                        
                    }     
                }
            
        }

        failure {
            script{
                    withCredentials([usernamePassword(credentialsId: 'TELEGRAM_BOT',
                    passwordVariable: 'TOKEN', usernameVariable: 'CHAT_ID')]) {
                            sendTelegramMessage("Deployment is Failed! ",
                             "${TOKEN}","${CHAT_ID}")
                      
                        
                    }     
                }
        }
    }
}

def sendTelegramMessage(String message, String token , String chatId) {
    // uppgrade to use Markdown version instead 
    def encodedMessage = URLEncoder.encode(message, "UTF-8")
    sh """
        curl -s -X POST https://api.telegram.org/bot${token}/sendMessage \\
        -d chat_id=${chatId} \\
        -d text="${encodedMessage}" > /dev/null
    """
}

