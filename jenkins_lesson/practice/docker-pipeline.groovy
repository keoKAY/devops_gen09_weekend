pipeline{
    agent any
    
    // additoinal configration 
    environment{
        DOCKER_IMAGE="myreactjs-image"
        DOCKER_USERNAME="james168"
    }
// Docker pipeline ( used docker command like in programming style )
    stages{
        stage("Show ENV variable "){
            steps{
                sh """
                echo "Full Image name is : ${DOCKER_USERNAME}/${DOCKER_IMAGE}"
            """ 
            }
           
        }
        stage("Deploy Nginx Container ")
        {
            
            steps{
                sh """
                docker stop nginx-cont || true 
                docker rm nginx-cont || true 
                """

            script{
                def nginxApp = docker.image("nginx:latest")
                nginxApp.inside{
                    sh """
              
                     ls -lrt 
                     nginx -v 
                    """
                }
                nginxApp.run(" --name nginx-cont -dp 8081:80")
            }
            }
        }
    }
}