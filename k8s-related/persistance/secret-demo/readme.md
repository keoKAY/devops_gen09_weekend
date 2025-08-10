## NOTE 


### Using imperative command to create the secret 
```bash
kubectl create secret generic \
    postgres-secret \
    --from-literal=POSTGRES_PASSWORD=password123
```


## Workign with secret for private docker registry 
```bash 
# Step1 : push image to private registry (nexusoss, ghcr.io )
docker images 
docker tag nginx:latest ghcr.io/username/image:tag 

docker login ghcr.io 
# username 
# password: token (write:package )


kubectl create secret docker-registry ghcr-secret \
  --docker-server=ghcr.io \
  --docker-username=<github-username> \
  --docker-password=<gh-token-read-package> \
  --docker-email=<github-email>
````