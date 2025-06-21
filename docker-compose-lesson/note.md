# note for docker compose 

docker compose : used to work with multiple containers environment 

### yaml 
Yaml aint a markup language 

it is a file that commonly used: 
- docker compose 
- configuration ( application.yaml )
- kubernetes ( yaml for manifest )

Yaml file often `.yaml or .yml`

* Simple comparison 
```bash

# json for student 
{
    "name": "james" ,
    "email: "james@gmail.com" , 
    "subjects": [
            "Java " , 
            "Python" 
        ], 
    "origin": {
        "city": "phnom penh" , 
        "country": "Cambodia"
    }
}

# yaml file sample for student 
name: james
email: james@gmail.com
subjects: 
    - Java
    - Python 
origin: 
    city: phnom penh 
    country: Cambodia 

```
- nginx ( nginx uses 443, 80 )
- docker ( docker compose ... )
- firewall rule : 80,443 
### Basic Command for docker compose 
```bash 
# to run all containers in detech mode
docker compose up -d 
docker compose down 
docker compose down -v


# check the configuration of the  docker compose file 
docker compose config 
docker compose config --services


sudo systemctl status nginx 


# if you have student.json and you want to convert it to the yaml file 
yq eval --input-format=json --output-format=yaml '.' student.json
```
*** 
### Pull Policy 
Policy for pulling the docker image 
* `missing`: pull from local first , if it doesn't exist pull from remote 
* `always`: always pull image from remote 
* `build`: don't pull image , but build image instead 
* never: never pull the image 


### Testing the docker pull policies 

```bash


#  restful api (upload images )
git clone https://gitlab.com/devops-trainings3/special-trainning/sample-projects/simple-fileupload-gradle.git

cd simple-fileupload-gradle
docker compose up -d # build 
docker compose up -d --build # to manually build it 

curl ifconfig.me



# if you have nginx-docker-compose.yaml 
docker compose -f nginx-docker-compose.yaml config
docker compose -f nginx-docker-compose.yaml up -d 

docker ps 
docker compose ps 
docker compose pause 
docker compose unpause 
docker compose -f nginx-compose.yaml ps|pause|unpause 

```