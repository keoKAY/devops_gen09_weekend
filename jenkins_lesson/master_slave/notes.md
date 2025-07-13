## Note for master slave in jenkins 
- run file.jar from the setup in jenkin UI 
- To setup agent in jenkins with SSH , there are two ways 
1. add public key of jenkins user inside the remote target user 

2. ssh-keygen on remote user, and save private key to jenkin credentials , add remote public-key to the authorized_keys


## For the server to connect to the slave , we will need 
- Install JDK 21 ( same version of Jenkin master )
```bash
sudo apt install openjdk-21-jdk
```




## Generic webhook 
We determine when to build the job 

We triggered build in our jenkins job by sending the event ourselves 
Example 
1. from clicking button or curl the website 

```bash 

# just request to the url , and it will build the job on jenkin 
# 123456 is our token 
# JENKINS_URL/job/master-slave-demo/build?token=TOKEN_NAME 


curl https://kk:your-jenkin-token@jenkins.devnerd.store/job/master-slave-demo/build?token=123456
```