FROM openjdk:8

COPY target/asr.jar .

COPY resources/acoustic_models/ acoustic_models

RUN wget -O acoustic_models/Spanish/cmu_spanish/es-20k.lm  http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=es-20k.lm

WORKDIR .


CMD ["java", "-jar", "asr.jar"]

