## NOte 
Helm -> package management for kubernetes 

Keyword for helms 
1. Chart 
2. Repository 
3. Release 

```bash
# first chart with helm 

helm create nginx-chart 
# to test your chart syntax (validation)
helm lint nginx-chart 

# to render your chart ( see the configuration after inject the value file )
helm template nginx-chart 
helm template nginx-chart --values prod-value.yaml

# to release your chart (deploy your app )
helm install nginx-release nginx-chart 
helm uninstall nginx-release 
# upgrade the old release 
helm upgrade nginx-release nginx-chart 
# if old release exist, we upgrade 
# if not we create a new one 
helm upgrade nginx-release nginx-chart --install 
helm list # show all the release 
helm list -n mesh-demo # n = namespace 
helm repo list 


# working nginx-release 
helm history nginx-release 
# if we want to revert back to version 
helm rollback nginx-release 1
# running test from 
helm test nginx-release 

# TODO
# package -> upload to registry 
# pull from registry and run it in the cluster
# create chart from scatch -> to under go template  
```

Syntax ( similar to programming is called GoTemplate)
- Jinja -> python related , {% %}, ansible
- GoTemplate -> go related , {{ }}, helm

#### Practice#01
- Use helm to create chart for reactjs 
- Reactjs -> domain , https  

```bash
# outside folder 
helm install planting-k8s nginx-chart --values nginx-chart/stag-values.yaml

helm upgrade planting-k8s nginx-chart --values nginx-chart/prod-values.yaml
```


Requirement: 
1. ClustterIssuer 
- letsencrypt-stagging
- letsencrypt-prod