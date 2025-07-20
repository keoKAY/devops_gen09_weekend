## NOTE 

- Ansible Config 
- Ansible Role 
- Ansible Galaxy 
- Ansible Vault 
- Group_Vars 

## HOMEWORK 
- Breakdown NFS playbook into roles 
- Create playbook to create google cloud instance 
    - create var file that contain machine info and spec 

### Ansible Role 
- Make your playbook reusable , dynamic and well-organized 
- When you want to use existing role on the internet 


### Ansible Vault 
- Prevent secret from being seen 
```bash 
ansible-vault --help 
ansible-vault create filename.yaml # type your vault password 
ansible-vault encrypt 
ansile-vault decrypt 
ansible-vault view 
ansible-vault edit 
```

### Ansible Galaxy 
is there all the communities , plugins are stored 
```bash 
ansible-galaxy role install geerlingguy.nginx 
ansible-galaxy role install geerlingguy.nginx -p roles
``