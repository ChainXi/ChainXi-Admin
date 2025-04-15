FROM openjdk:17-jdk-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
RUN apk add --no-cache freetype ttf-dejavu

ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENV TZ=Asia/Shanghai
ENV ARGS=""
EXPOSE 8080
CMD java ${JAVA_OPTS} -jar app.jar $ARGS
