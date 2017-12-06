FROM openjdk:8

COPY target/asr.jar .
	
WORKDIR .
# WORKDIR /usr/src/listener/model/

# RUN wget -O frozen_inference_graph.pb https://www.dropbox.com/s/g301ym64oruo6u2/frozen_inference_graph.pb?dl=0
# WORKDIR /usr/src/

CMD ["java", "-jar", "asr.jar"]

