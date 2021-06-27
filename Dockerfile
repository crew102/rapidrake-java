FROM ubuntu

ADD . /home/rapidrake-java

WORKDIR /home/rapidrake-java

RUN apt-get update; \
    apt-get install -y --no-install-recommends openjdk-8-jdk maven wget; \
    update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java; \
    mkdir model-bin; \
    wget -P model-bin http://opennlp.sourceforge.net/models-1.5/en-sent.bin; \
    wget -P model-bin http://opennlp.sourceforge.net/models-1.5/en-pos-maxent.bin

ENTRYPOINT ["mvn", "test"]
