pipeline{
    agent any
// Docker pipeline ( used docker command like in programming style )
    stages{
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
                     whoami 
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