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