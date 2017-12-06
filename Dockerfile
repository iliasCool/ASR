FROM openjdk:8

COPY target/asr.jar .
	
WORKDIR .
# This code will be used to bypass the 25Mb github limit, if there is a need to upload a large file
# WORKDIR /usr/src/listener/model/

# RUN wget -O frozen_inference_graph.pb https://www.dropbox.com/s/g301ym64oruo6u2/frozen_inference_graph.pb?dl=0
# WORKDIR /usr/src/

CMD ["java", "-jar", "asr.jar"]

