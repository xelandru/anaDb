FROM ubuntu:14.04

ENV DEBIAN_FRONTEND noninteractive
ENV TERM xterm
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV JAVA_OPTS -Xms2g -Xmx2g -XX:+UseParallelGC

# Install JDK + other utilities
RUN apt-get update -y \
	&& apt-get install -y software-properties-common python-software-properties \
	&& add-apt-repository ppa:webupd8team/java \
	&& apt-get update -y \
    && echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true \
        | sudo /usr/bin/debconf-set-selections \
    && apt-get install -y \
           oracle-java8-installer \
           oracle-java8-set-default \
    && apt-get install -y nano \
		   htop openssh-server \
		   curl wget telnet \
		   zip


#DERBY STUFF
ENV DERBY_VERSION=10.14.2.0


ENV DERBY_HOME=/derby
ENV DERBY_LIB=${DERBY_HOME}/lib
ENV CLASSPATH=${DERBY_LIB}/derby.jar:${DERBY_LIB}/derbynet.jar:${DERBY_LIB}/derbytools.jar:${DERBY_LIB}/derbyoptionaltools.jar:${DERBY_LIB}/derbyclient.jar


RUN wget https://dist.apache.org/repos/dist/release/db/derby/db-derby-$DERBY_VERSION/db-derby-$DERBY_VERSION-bin.tar.gz \
       && tar xvfz db-derby-$DERBY_VERSION-bin.tar.gz \
       &&  mv /db-derby-$DERBY_VERSION-bin $DERBY_HOME

WORKDIR $DERBY_HOME

ENV SCRIPT=init.sql
COPY $SCRIPT $DERBY_HOME/
RUN java org.apache.derby.tools.ij $SCRIPT
EXPOSE 1527
CMD ["java", "-Dderby.stream.error.field=java.lang.System.out", "org.apache.derby.drda.NetworkServerControl", "start", "-h", "0.0.0.0"]
