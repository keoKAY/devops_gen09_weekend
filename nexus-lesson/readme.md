## NOte 
Containerization 
Docker ( Dockerfile, Docker image, Container , Network , Volume , ...)

- We use docker image in order to run to containers 


### Container Registry 
> Repository used for storing the images ( docker images )
- Dockerhub , registry.gitlab.com , ghcr.io 

-> **Nexus OSS Repository** : used in order to store docker images 
Self-Hosted 
- Docker Image registry
- Helm Registry

*** 

```bash
# To access the web UI 
http://35.213.173.163:8081/

# For docker image , we will use 
http://35.213.173.163:5000
```

## BLOB STORE 
think of it as a storage device (SSD, HDD) , where you give the name to it 
- docker-hosted-blob 
### REPOSITORY 
- docker-hosted


## Pushing the docker image to the http://ip 
```bash
docker pull nginx 
docker tag nginx:latest 35.213.173.163:5000/nginx-nexus:v1.0.0 
docker push  35.213.173.163:5000/nginx-nexus:v1.0.0

docker login -u admin http://35.213.173.163:5000
```
### Configure the domain name for UI and registry 
```bash
nexus.domain.dev -> 8081
docker-registry.domain.dev  -> 5000 
```