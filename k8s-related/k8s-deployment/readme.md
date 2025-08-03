## Rollout and rollback 
```bash
# kubectl apply -f file.yaml --record

kubectl rollout status deployment/nginx-app-blue
# to see the revision number 
kubectl rollout history deployment/nginx-app-blue

# just like you Ctrol+Z
kubectl rollout undo deployment/nginx-app-blue
kubectl rollout undo deployment/nginx-app-blue --to-revision=2

```