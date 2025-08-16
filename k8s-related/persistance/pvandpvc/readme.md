## NOTE for PVC and PV 
> helm will be used in this project as well 


- PV ( Persistence Volume ) 
    Think of it as a block of storage that able to store thing 

- PVC ( Persistence Volume Claims )
    think of it as a request to use specific PV 

#### There are three diffferent type of access mode 
- RWO -> Read Write Once 
- ROX -> ReadOnly Many 
- RWX -> ReadWrite Many 

#### There are three `reclaim policies` 
- Retain -> Delete PVC, PV will remain 
- Delete -> Delete PVC, PV also delete 
- Recycle -> 


#### Provisioning ( there are two type of provisions )
- Static Provisioning : 
    do create pv, and pvc by yourself 
- Dynamic Provisioning :
     only need to create pvc, pv will created auto

```bash
helm version


alias k=kubectl 
alias h=helm


# to see the event or issue with the containercreating state 
kubectl get pod 
kubectl describe pod <pod-name> 
# create folder from the nfs path 
mkdir /srv/nfs_shared/spring_images

# ingress, nodePort type service 
kubectl port-forward svc/spring-uploader-svc  30003:80
```


#### Dynamic Provisioning 
-> PV , PVC 
-> PVC bind with PV 

Automatically create PV, from the PVC config 
```bash 
helm repo list 


# adding repo 
helm repo add nfs-subdir-external-provisioner https://kubernetes-sigs.github.io/nfs-subdir-external-provisioner/


# create kubernetes artifacts / manifests
helm install nfs-subdir-external-provisioner nfs-subdir-external-provisioner/nfs-subdir-external-provisioner \
    --set nfs.server=10.148.0.2 \
    --set nfs.path=/srv/nfs_shared

kubectl get storageclass 
kubectl get pv
kubectl get pvc 
```