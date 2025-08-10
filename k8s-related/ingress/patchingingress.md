## Update nginx configuration for our clusters 

1. Access your dashboard first 
2. Edit the ingress configurations 


```bash 
F0809 07:03:09.996013       8 main.go:64] flags --publish-service and --publish-status-address are mutually exclusive
```

```bash
- '--watch-ingress-without-class=true'
- '--publish-status-address=10.148.0.2,10.148.0.17,10.148.0.18,10.170.0.2,10.170.0.3'
# comment the --public-service part 
```

## on the resource restricting you can allow the master 
```bash
kubectl taint nodes node1 \
    node-role.kubernetes.io/master:NoSchedule-

# the control-plane role 
kubectl taint nodes node1 \
    node-role.kubernetes.io/control-plane:NoSchedule-

# To taint the node of the control-plane role 
kubectl taint nodes node1 \
    node-role.kubernetes.io/control-plane:NoSchedule


```
# finding the right FQDNS 
```bash
kubectl run -it dns-test --rm --restart=Never --image=busybox:1.36 sh

#If you don't see a command prompt, try pressing enter.
nslookup mynginx-service.default.svc.mycluster

#Server:         169.254.25.10
#Address:        169.254.25.10:53
#Name:   mynginx-service.default.svc.mycluster
#Address: 10.233.58.50

# to get the clusterip for the nameserver of core-dns
kubectl get svc -n kube-system  | grep "coredns"
```