#FROM openjdk:17-jdk-slim
#RUN sed -i 's/deb.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list
#RUN sed -i 's/security.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list
#RUN apt-get clean
#RUN apt-get update
#RUN apt-get install -y --no-install-recommends fontconfig
#some environments are abnormal. Please switch to the slim image.
FROM eclipse-temurin:17-jdk-noble

ADD ./target/*.jar app.jar
ENV TZ=Asia/Shanghai
ENV ARGS=""
EXPOSE 8080
CMD java ${JAVA_OPTS} -jar app.jar $ARGS
