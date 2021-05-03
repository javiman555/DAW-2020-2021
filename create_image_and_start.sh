#!/bin/bash
cd frontend/trec
ng build --prod --base-href="/new/"
cd ../..
mkdir backend/src/main/resources/static/new
cp -r frontend/trec/dist/ejem0/* backend/src/main/resources/static/new
docker build -f Docker/Dockerfile -t trec .
docker-compose -f Docker/docker-compose.yml up