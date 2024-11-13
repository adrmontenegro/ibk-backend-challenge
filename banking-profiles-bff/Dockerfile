# Use an official Node.js runtime as the base image
FROM node:16-alpine 

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json (if you use it)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of your application code
COPY . .

# Expose the port your application listens on
EXPOSE 3000

# Start your application
CMD [ "npm", "run", "start:prod" ] # Use your production start script
