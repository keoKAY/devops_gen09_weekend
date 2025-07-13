pipeline{
    agent any 
    stages{
       
        stage("Work 1 "){
            
            agent {
            label "Worker-01"
        }
            steps{
                sh """ 
                whoami 
                curl ifconfig.me
                pwd 

                """
            }
        }

        stage("Work 2 "){
            // have to configure label for the master 
            // default "built-in"
            agent {
                label "master-node"
            } 
            steps{
                sh """ 
                curl ifconfig.me
                whoami 
                pwd 

                """
            }
        }
    }
}