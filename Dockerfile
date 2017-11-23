FROM openjdk:8
	

COPY target/asr.jar .
	
WORKDIR .

CMD ["java", "-jar", "asr.jar"]

