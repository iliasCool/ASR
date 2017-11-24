FROM openjdk:8
	
COPY resources/acoustic_models .

COPY target/ASR.jar .
	
WORKDIR .

CMD ["java", "-jar", "asr.jar"]

