FROM openjdk:17-jdk-slim

RUN mkdir /app
WORKDIR /app

# Gradle Wrapper 및 build.gradle 파일 복사
COPY ./com.nameslowly.coinauctions.server/gradlew /app/gradlew
COPY ./com.nameslowly.coinauctions.server/gradlew.bat /app/gradlew.bat
COPY ./com.nameslowly.coinauctions.server/build.gradle /app/build.gradle
COPY ./com.nameslowly.coinauctions.server/gradle/ /app/gradle/
COPY ./com.nameslowly.coinauctions.server/src/ /app/src/

# 필수 패키지 설치 (Debian 기반)
RUN apt-get update && apt-get install -y findutils

# 프로젝트를 빌드
RUN /app/gradlew build

# 빌드된 JAR 파일을 임시 디렉토리로 복사
RUN cp /app/build/libs/*.jar /app/
RUN ls

# 빌드된 결과를 실행
ENTRYPOINT ["java", "-jar", "/app/app-0.0.1-SNAPSHOT.jar"]
