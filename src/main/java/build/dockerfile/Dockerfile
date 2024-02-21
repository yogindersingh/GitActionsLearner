FROM tomcat

USER root

expose 9001

WORKDIR application

COPY target/github-actions-playground.jar .

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","github-actions-playground.jar"]

