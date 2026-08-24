# Start your image with a postgres base image
FROM postgres:16

# The /Library directory should act as the main application directory
WORKDIR /Library

# Set environment variables for PostgreSQL
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=biblioteca

# Expose PostgreSQL port for DBeaver connections
EXPOSE 5432


