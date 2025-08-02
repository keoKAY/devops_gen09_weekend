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
kubectl -n kubernetes-dashboard create token admin-user --duration=24h


### 1.POD 
```bash
kubectl create ns namespace-demo 
kubectl apply -f pod.yaml
kubectl replace -f pod.yaml 
# default namespace , type like this 
kubectl delete pod/name 

# if namespac eis something else 
kubectl delete pod/name -n namedspace
kubectl get pod 
kubectl logs pod/pod-name 
kubectl get pod -o wide 

# to see the full confnig in yaml format 
kubectl get pod/name -o yaml 



# imperative command
kubectl run my-nginx --image=nginx:1.22.1 --restart=Never 

# one container per pod 
# to login to your pod ( if you have only one container )
kubectl exec -it pod-name -- bash
# to login to container of your pod (multiple container )
kubectl exec -it pod-name -c container-name -- bash
kubectl describe pod/pod-name # describe more info of pods such as name, node selector , event 
```
### 2. Replicaset 
Used for maintaining a specific number of pods 

```bash
kubectl get rs 
kubectl get replicaset 
watch kubectl get pod 


```


### 3. Deployment
```bash 
kubectl get deployment 
kubectl get deploy 

```