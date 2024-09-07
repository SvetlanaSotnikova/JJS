FROM openjdk:21

COPY out/artifacts/JJS_jar/JJS.jar /tmp/JJS.jar
WORKDIR /tmp
CMD ["java", "-jar", "/tmp/JJS.jar"]
