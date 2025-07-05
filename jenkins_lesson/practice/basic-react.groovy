pipeline {
    agent any

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/keoKAY/reactjs-devop8-template'
                sh 'ls -lrt '
                sh "pwd"
                sh """
                echo "This is the code clone " 
                ls 
                docker version 
                """

            }
        } \
    https://github.com/keoKAY/devops_gen09_weekend.gitlyvanna544@gmail.om

        stage("Build"){
            steps{
                sh """
                    docker build -t jenkins-react-pipeline . 
                """
            }
        }


        stage("Deploy"){
            steps{
                sh"""
                docker stop reactjs-cont || true 
                docker rm reactjs-cont || true 


                docker run -dp 3000:80 \
                    --name reactjs-cont \
                    jenkins-react-pipeline
                """

            }
        }
    }
}
