@echo off
docker pull mysql:8.0.23
docker-compose -f Docker\docker-compose.yml down
docker-compose -f Docker\docker-compose.yml up