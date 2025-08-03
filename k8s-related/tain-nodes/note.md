## TAINT NODES 

### Taint the node 
 To prevent the master node in a Kubernetes cluster from running any services
```bash
kubectl taint nodes node2 node-role.kubernetes.io/master=:NoSchedule

# to check the tains status of the node 
kubectl get nodes -o json | jq '.items[] | {name: .metadata.name, taints: .spec.taints}'
```

### Untaint the node 
- If you later decide to allow pods to run on the master node, you can remove the taint using the following command:
```bash
kubectl taint nodes node2 node-role.kubernetes.io/master-
kubectl get node # Get nodes name
```
```bash
kubectl taint nodes node5 service=disabled:NoSchedule

#*service=disabled (Any key=value pair that you want notice or comment)
kubectl taint node node5 service-
```