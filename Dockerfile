FROM openjdk:8

COPY resources/acoustic_models .

COPY target/asr.jar .
	
WORKDIR .


CMD ["java", "-jar", "asr.jar"]

