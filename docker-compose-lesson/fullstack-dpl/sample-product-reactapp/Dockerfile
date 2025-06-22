#docker file for reactjs service
# Use a Node.js base image
FROM node:alpine as builder
# Set the working directory
WORKDIR /app
COPY package*.json ./
#ENV REACT_APP_BASE_URL=http://spring-svc:8080
RUN npm install
COPY . .
# Build the React.js application
RUN npm run build



# Use a lightweight web server such as nginx for serving the built application
FROM nginx:alpine as prod
# Copy custom nginx configuration
COPY default.conf /etc/nginx/conf.d/default.conf
# Copy the built application from the previous step
COPY --from=builder /app/build /usr/share/nginx/html
# Expose port 80
EXPOSE 80
# Start the nginx server in foreground
CMD [ "nginx", "-g", "daemon off;" ]
