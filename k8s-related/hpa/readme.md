## NOTE 
> Install tool for load testing 
ex. sending 10000 requests to the service 
```bash
sudo apt update
sudo apt install apache2-utils -y 
ab # apache benchmark  


ab -n 10000 -c 1000 http://10.233.44.230/

```