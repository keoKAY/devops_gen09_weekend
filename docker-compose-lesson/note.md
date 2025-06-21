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

### Basic Command for docker compose 
```bash 
# to run all containers in detech mode
docker compose up -d 
docker compose down 
docker compose down -v


# check the configuration of the  docker compose file 
docker compose config 
docker compose config --services
```
