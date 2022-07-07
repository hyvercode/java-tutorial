FROM maven:3.8.6-jdk-11
WORKDIR ./java-spring-boot-day2
COPY . .
RUN mvn clean install
CMD mvn clean verify sonar:sonar \
      -Dsonar.projectKey=java-spring-day2-service \
      -Dsonar.host.url=http://localhost:9000 \
      -Dsonar.login=sqp_a80ff4a8070957db0edf321896a9bd22ba5d8fcb
CMD mvn spring-boot:run