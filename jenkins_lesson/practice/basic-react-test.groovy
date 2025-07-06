pipeline {
    agent any

    tools{
        nodejs "nodejs-lts"
    }
// Jest ( Testing framework for Frontend )
    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/keoKAY/reactjs-devop8-template'

            }
        } 

        stage("Run Test"){
            steps{
                sh """
                    npm install --force
                    npm test 
                """
            }



        }

        
        stage("Build"){
            steps{
                
                sh """
                    docker build -t lyvanna544/jenkins-react-pipeline . 
                """
            }
        }

        stage("Push to Dockerhub "){
            steps{
                withCredentials([gitUsernamePassword(
                    credentialsId: 'DOCKERHUB',
                     usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD')]) {
                      
                      sh """
                    echo "$DOCKER_PASSWORD |  docker login -u $DOCKER_USERNAME --stdin   
                docker push lyvanna544/jenkins-react-pipeline 
                """
                    }

                
            }
        }


        stage("Deploy"){
            steps{
                sh"""
                docker stop reactjs-cont || true 
                docker rm reactjs-cont || true 


                docker run -dp 3000:80 \
                    --name reactjs-cont \
                    lyvanna544/jenkins-react-pipeline
                """

            }
        }

        stage("Add Domain Name"){
            steps{
                sh """

                which certbot
                certbot --version 
                # write reverse proxy config 
                # sudo nginx -s reload 
                """ 
            }
        }


    }
}
