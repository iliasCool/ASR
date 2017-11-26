FROM openjdk:8

COPY resources/acoustic_models/English/en-us acoustic_models/English/en-us
COPY resources/acoustic_models/English/cmudict-en-us.dict acoustic_models/English   
COPY resources/acoustic_models/English/cmudict-en-us.dict acoustic_models/English   

COPY target/asr.jar .
	
WORKDIR .


CMD ["java", "-jar", "asr.jar"]

