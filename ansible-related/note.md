## INstall ansible 

```bash
sudo apt update
sudo apt install software-properties-common
sudo add-apt-repository --yes --update ppa:ansible/ansible
sudo apt install ansible
``` 

## Requirements 
1. Install Ansible in your jenkins machine ( or local machine (WSL) , linux )

Jenkin Machine -> Worker 1 
                -> First GCP Instance 

## IaC , CaC 
### Infrastructure as Code 
    -> Write code to define the structure of your infrastructure 
    -> If you want to create a new infrastructure , just need to run your code 

> Ex. Terraform , Ansible
### Configuration as Code 
> Ansible, Chef, Puppet , .... 
    -> Ex: 
        - Install nginx, docker, docker compose, 
        - Nexus OSS , Jenkins , K8s cluster 
    -> With CaC , you can define your configujration or software installation like you write a code ! 
    -> Provisioning 



### Related to Ansible Key Words 
> Naming style : inventory.yaml , ini 
                    hosts.yaml , hosts.ini
Inventory : 
    - .yaml, .ini file 
    - configure about machines that ansible can manage 

Playbooks: 
    - yaml files defined tasks that you want to do on the machines 

*** 
### How to configure SSH for ansible 
```bash
ssh-keygen # on ansible ( Control Node )
# Copy id_rsa.pub to authorized_keys inside slave machines 

```
### Adhoc command 
```bash 
# adhoc command syntax 
ansible -i inventory.ini machine-name -m ping 
ansible -i inventory.ini group-name -m ping

# SSH for the inventory  
ansible -i inventory.ini localhost -m ping 
ansible -i inventory.ini dev -m ping 
ansible -i inventory.ini prod -m ping 
ansible -i inventory.ini all -m ping 


# run adhoc command module 
ansible -i inventory.ini all -m command -a "uptime"
ansible -i inventory.ini all -m apt -a "name=nginx state=present" # absent: remove 
ansible -i inventory.ini all -m apt -a "name=nginx state=present" --become # sudo 
```