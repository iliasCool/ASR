FROM openjdk:8

COPY resources/acoustic_models/English/en-us acoustic_models/English/en-us
COPY target/asr.jar .
	
WORKDIR .


CMD ["java", "-jar", "asr.jar"]

