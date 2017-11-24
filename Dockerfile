FROM openjdk:8
	
COPY resources/acoustic_models .

COPY target/ASR-1.1-jar-with-dependencies.jar .
	
WORKDIR .

CMD ["java", "-jar", "asr.jar"]

