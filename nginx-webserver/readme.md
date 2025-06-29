## Note for nginx 


## Domain name 
string that represent IP address 
facebook.com -> 157.240.7.35


```bash
nslookup domain.com
nslookup facebook.com

# to see your public ip 
curl ifconfig.me


reactjs-gcp.devnerd.store -> 35.213.173.163

nslookup reactjs-gcp.devnerd.store


# deploy service 
docker rm -f nginx_cont 

docker run -dp 3000:80  --name reactjs_cont  69966/reactjs-image-gcp:3151a133
```

## NGINX 
```bash
# location for writing the nginx configuration ( reverse proxy config )
cd /etc/nginx/conf.d
cd /etc/nginx/sites-available 
# if you want the configuration inside the site-available to work , you need to link to sites-enabled 


sudo vim reactjs-gcp.conf 
```

```nginx
# configuration for reverse proxy 
# sudo vim reactjs-gcp.conf 

server{
    listen 80; 
    listen [::]:80; 
    server_name reactjs-gcp.devnerd.store; 

    location / {
        proxy_pass http://localhost:3000;
        # proxy_set_header used to properly forward the request header to the proxy_pass service. 
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

    } 

}

```
### Certbot for https 
```bash 
sudo apt install certbot -y 
sudo apt install python3-certbot-nginx -y 
sudo certbot --nginx -d reactjs-gcp.devnerd.store   
sudo certbot --nginx 

# certificates (https )
# http challenges ( public ip : 80 ) , dnsChallenge
## email , domain -> current server 
```

### Adding domain name and https for the other services
- portainer 
- nexus repository 

* Sub domain to be created in Namecheap 
```bash 
portainer-gcp.devnerd.store 
nexus-gcp.devnerd.store 
nexus-cr.devnerd.store 

        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";


```

* portainer.conf 
```bash 
# in order to confirm subdomain is usable or not 
nslookup portainer-gcp.devnerd.store 
#  you should see name , and address 
```
```nginx
# /etc/nginx/conf.d
# portainer.conf 
server{
    listen 80; 
    listen [::]:80; 

    server_name portainer-gcp.devnerd.store; 
    listen / {
        proxy_pass https://localhost:9000; 

        # Websocket support 
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";

        # Prevese the Client IP and requests 
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```
```bash
sudo nginx -t 
# to restart the nginx to get the config 
sudo systemctl restart nginx 
sudo nginx -s reload 
```

* adding domain name and https for nexus 
```bash 
nexus-gcp.devnerd.store 
nexus-cr.devnerd.store 

# nexus-gcp.conf 
server{
    listen 80; 
    listen [::]:80; 
    server_name nexus-gcp.devnerd.store; 
    location / {
        proxy_pass http://localhost:8081;
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

    } 

}

# nexus-cr.conf 
server{
    listen 80; 
    listen [::]:80; 
    server_name nexus-cr.devnerd.store; 
    location / {
        proxy_pass http://localhost:5000;
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

    } 

}

sudo nginx -t 
sudo nginx -s reload 
sudo certbot --nginx -d nexus-gcp.devnerd.store \
    -d nexus-cr.devnerd.store 


# self-signed certificate 

# working with container registry domain name 
nexus-cr.devnerd.store 
```