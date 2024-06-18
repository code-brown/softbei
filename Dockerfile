# 使用 OpenJDK 作为基础镜像
FROM openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /app

# 添加 JAR 文件到容器中
COPY target/Trainer-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 运行应用
ENTRYPOINT ["java","-jar","app.jar"]
