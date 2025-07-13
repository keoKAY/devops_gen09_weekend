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
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
    
                      sh """
                    echo '$PASSWORD' |  docker login -u $USERNAME --password-stdin   
                docker push lyvanna544/jenkins-react-sonarqube-pipeline:${env.BUILD_NUMBER} 
                """
                    }
            }
        }
        

    }
// post actions or post scripts  

    post{
        success{
                script{
                    withCredentials([usernamePassword(credentialsId: 'TELEGRAM_BOT',
                    passwordVariable: 'TOKEN', usernameVariable: 'CHAT_ID')]) {

                            // 
                            def successMessage="""
                            Deployment is Success 👍
                            🟢 Access Service: https://sonarqube.devnerd.store            
                            📥  Job Name: ${env.JOB_NAME}
                            🎱   Build Number: ${env.BUILD_NUMBER}
                            """ 

                            sendTelegramMessage("${successMessage}",
                             "${TOKEN}","${CHAT_ID}")
                        
                        
                    }     
                }
            
        }

        failure {
            script{
                    withCredentials([usernamePassword(credentialsId: 'TELEGRAM_BOT',
                    passwordVariable: 'TOKEN', usernameVariable: 'CHAT_ID')]) {
                             def errorMessage="""
                            Deployment result in failures 🔥
                            🟢 Access Report: https://sonarqube.devnerd.store/dashboard?id=reactjs-devops8-template           
                            📥  Job Name: ${env.JOB_NAME}
                            🎱   Build Number: ${env.BUILD_NUMBER}
                            """ 


                            sendTelegramMessage("${errorMessage}",
                             "${TOKEN}","${CHAT_ID}")
                      
                        
                    }     
                }
        }

        always {
            // built-in function used to clean the workspace of jenkins 
            echo "Clearing the workspace of ${env.JOB_NAME} "
            cleanWs()
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

