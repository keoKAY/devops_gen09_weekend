## NOTE 
> Working with argocd inside the k3s cluster 


1. Adding domain name for our argocd service 
```bash
 kubectl get clusterissuer 
 # letsencrypt-prod -> ns= default 

 kubectl get svc -A | grep "argocd-server"
 # get only service inside the argocd namespace 
 kubectl get svc -n argocd

# username of argo is: admin 
# get your initial password 
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d

```

## Working with webhook

```bash
123mysecretdemo

kubectl edit secret argocd-secret -n argocd
kubectl describe secret argocd-secret -n argocd


echo -n "123mysecretdemo" | base64 

data: 
    webhook.github.secret: <your-base64-value> 
    
```
