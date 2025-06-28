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
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;

    } 

}

```