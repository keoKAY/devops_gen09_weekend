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
# upgrade the old release 
helm upgrade nginx-release nginx-chart 

# if old release exist, we upgrade 
# if not we create a new one 
helm upgrade nginx-release nginx-chart --install 


```

Syntax ( similar to programming is called GoTemplate)
- Jinja -> python related , {% %}, ansible
- GoTemplate -> go related , {{ }}, helm