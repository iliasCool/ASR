FROM openjdk:8
	
COPY resources/acoustic%20models .

COPY target/asr.jar .
	
WORKDIR .

CMD ["java", "-jar", "asr.jar"]

