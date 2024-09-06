FROM openjdk:21

COPY out/artifacts/JJS_jar /tmp/JJS.jar
WORKDIR /tmp
CMD["java","-jar","/tmp/Sem4.jar"]