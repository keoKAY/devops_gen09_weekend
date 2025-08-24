## NOTE for working with kubernetes dashboard 

```bash 
kubectl get all -n kube-system


# make sure to change from NodePort to ClusterIP 
kubectl get svc -n kube-system 
kubectl edit svc kubernetes-dashboard -n kube-system
# \type

k8s-dashboard.devnerd.store 
# test DNS -> IP 
nslookup k8s-dashboard.devnerd.store



kubectl get ingress -n kube-system
kubectl get cert -n kube-system # look for Ready State 
```


## IMPORTANT NOTE 
- If you removed the taint (untaint) for all the masters 
you can use master IP for the dns record 
- If not, use IP of workers instead