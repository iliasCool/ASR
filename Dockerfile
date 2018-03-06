FROM openjdk:8

COPY target/asr.jar /home

COPY resources/acoustic_models/ /home/acoustic_models

#WORKDIR  /home/acoustic_models/Spanish/cmu_spanish/
#RUN wget -O es-20k.lm  http://object-store-app.eu-gb.mybluemix.net/objectStorage?file=es-20k.lm

WORKDIR /home

CMD ["java", "-jar", "asr.jar"]
