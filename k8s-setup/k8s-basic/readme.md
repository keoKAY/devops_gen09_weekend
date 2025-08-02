## Basic command
```bash
kubectl get node
kubectl get node -o wide 
kubectl get pod 
kubectl get pod -A
```

### To run kubectl without having to type sudo 
```bash
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

```
### Configure Dashboard of K8s
> addon.yaml -> enabled kubenete-dashboard 


```bash
# to show all the services inside our clusters 
kubectl get all -A

# to access our dashboard , we will use nodeport for temp access 
kubectl get svc -n kube-system
kubectl edit service/kubernetes-dashboard -n kube-system
# change type: ClusterIP -> NodePort
kubectl get svc -n kube-system # find svc of dashboard with port 
# to access our kubernetes dasboard 
https://35.213.173.163:31327 


# generate the token in order to access the dashboard with RBAC 
```
* create serviceaccount and clusterrole to create the token 
```yaml
# k3s-svcacc-clusterrole.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
 name: admin-user
 namespace: kubernetes-dashboard
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
 name: admin-user
roleRef:
 apiGroup: rbac.authorization.k8s.io
 kind: ClusterRole
 name: cluster-admin
subjects:
  - kind: ServiceAccount
    name: admin-user
    namespace: kubernetes-dashboard
```