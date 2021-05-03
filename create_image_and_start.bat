@echo off
cd frontend/trec
ng build --prod --base-href="/new/"
echo "Build hecho"
cd ../..
echo "Me he movido"
cp frontend/trec/dist/ejem0/* backend/src/main/resources/static/new
docker build -f Docker\Dockerfile -t trec .
docker-compose -f Docker\docker-compose.yml up