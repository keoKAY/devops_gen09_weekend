## Note for jenkins 
### Requirement 
- docker & docker compose 
- nginx 
- jenkins installation 


### Installation for Jenkins 
- for dev , you can use docker compose 
- for prod , better performance 
```bash
sudo apt install openjdk-21-jdk
sudo wget -O /etc/apt/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins
```