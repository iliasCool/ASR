FROM openjdk:8

COPY target/asr.jar .

COPY resources/acoustic_models/ .

WORKDIR .


CMD ["java", "-jar", "asr.jar"]

