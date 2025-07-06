// Run or customize command inside containers as if it's  is an agent 
pipeline{
    agent {
        docker {
            image "node:lts"
            args "-u root"
        }
    }

    stages{
        // All the stages will be executed inside Node:lts container 
        stage("Check Command inside docker agent "){
            steps{
                sh """
                node --version 
                pwd 
                ls -lrt 

                """
            }
        }

               
    }
}