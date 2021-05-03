# FROM maven:3.8.1-jdk-11
# COPY backend /backend
# RUN cd /backend \
#     && mvn clean install
# RUN mv /backend/target/TREC-0.0.1-SNAPSHOT.jar /TREC-0.0.1-SNAPSHOT.jar
# RUN rm -rf /backend
# CMD ["java","-jar","/TREC-0.0.1-SNAPSHOT.jar"]

FROM node:14 as builderfront
COPY frontend /frontend
WORKDIR /frontend/trec
RUN npm install
RUN npm install -g @angular/cli
RUN ng build --prod --base-href='/new/'

FROM maven:3.8.1-jdk-11 as builderback
COPY backend /backend
RUN mkdir /backend/src/main/resources/static/new
COPY --from=builderfront /frontend/trec/dist/ejem0/* /backend/src/main/resources/static/new
WORKDIR /backend
RUN mvn package

FROM openjdk:11-jre
COPY --from=builderback /backend/target/*.jar /usr/app/TREC-0.0.1-SNAPSHOT.jar
WORKDIR /usr/app
CMD [ "java", "-jar", "TREC-0.0.1-SNAPSHOT.jar" ]