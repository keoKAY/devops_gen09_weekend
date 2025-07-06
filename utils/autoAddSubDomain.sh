#!/bin/bash

domain_name="$1"
service_port="$2"
# check to show sudoer message can run this 

# check if the domain anme and service port is provided 
if [ -z $domain_name ]; then 
    echo "Domain name must not be empty " 
    exit 1 
fi 
if [ -z $service_port ]; then 
    echo "service_port must be provided " 
    exit 1 
fi 


# if the config file already exists , no need to writer another file 

NGINX_CONF_DIR="/etc/nginx/conf.d"

if grep -rq "server_name.*${domain_name}.devnerd.store" "$NGINX_CONF_DIR"; then
    #echo "true"
    echo "File exist ! No need to provide another reverse proxy config " 
    #exit 0
else
    echo "Write the reverse proxy config " 
    echo "1. Write reverse proxy config for service " 

tee "${NGINX_CONF_DIR}/${domain_name}.conf" > /dev/null << EOF
    server{
        listen 80; 
        listen [::]:80; 
        server_name ${domain_name}.devnerd.store; 

        location / {
            proxy_pass http://localhost:${service_port};
            proxy_set_header Host \$http_host;
            proxy_set_header X-Real-IP \$remote_addr;
            proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto \$scheme;

        } 

    }
EOF


echo "Test and reload the configuration " 
sudo nginx -t && sudo nginx -s reload

echo "Add https for the ${domain_name}.devnerd.store"
sudo certbot --nginx -d "${domain_name}.devnerd.store" 
#    exit 1
fi


