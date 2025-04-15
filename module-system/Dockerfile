#http://cnblogs.com/niceyoo/p/13796792.html
FROM openjdk:17-jdk-slim
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENV TZ=Asia/Shanghai
ENV ARGS=""
EXPOSE 8080
CMD java ${JAVA_OPTS} -jar app.jar $ARGS
