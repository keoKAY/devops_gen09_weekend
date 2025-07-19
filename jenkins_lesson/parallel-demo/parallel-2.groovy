pipeline{
    agent any // any agent that are available (master )
    stages{
        stage("Parallel"){
        parallel{
            stage("Work 1 "){
                steps{
                    echo "Work1 is completed "
                }
            }

            stage("Work 2 "){
                steps{

                    echo "Work2 is completed "
                }
            }
        }
    }
    }
}