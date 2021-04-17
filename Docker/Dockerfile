FROM maven:3.8.1-jdk-11
COPY backend /backend
RUN cd /backend \
    && mvn clean install
RUN mv /backend/target/TREC-0.0.1-SNAPSHOT.jar /TREC-0.0.1-SNAPSHOT.jar
RUN rm -rf /backend
CMD ["java","-jar","/TREC-0.0.1-SNAPSHOT.jar"]