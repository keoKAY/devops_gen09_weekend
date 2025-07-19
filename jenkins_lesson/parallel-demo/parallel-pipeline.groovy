pipeline{
    agent any // any agent that are available (master )
    stages{
        stage("Work 1 "){
            steps{

            echo "Completing the work 1  "
            }
        }

        stage("Worke 2 "){
            parallel{
                stage("Step 1 "){
                    steps{
                        echo "Step 1 "
                    }
                }
                stage(" Step 2 "){
                    steps{
                        echo "Step 2 "
                    }
                }
            }
           
        }

        stage("Work 3 "){
            steps{

            echo "Completing the work 2 "
            }
        }
    }
}