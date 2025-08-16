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
```