# Use Payara image with OpenJDK 21 as the base image
FROM payara/server-full:6.2024.2-jdk21

# Switch to root user to install unzip
USER root

# Install unzip
RUN apt-get update && apt-get install -y unzip && apt-get install curl

# Set environment variable for deployment directory
ENV DEPLOY_DIR=/opt/payara/deployments

# Create the 'dataverse' folder inside the deployment directory
RUN mkdir -p ${DEPLOY_DIR}/dataverse

# Copy the .war file into the container (for extraction)
COPY target/dataverse.war /tmp/dataverse.war

# Unzip the .war file to the 'dataverse' folder
RUN unzip /tmp/dataverse.war -d ${DEPLOY_DIR}/dataverse && rm /tmp/dataverse.war

# Switch back to payara user after installation
USER payara
