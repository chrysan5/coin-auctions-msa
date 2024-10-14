FROM openjdk:17-jdk-slim

RUN mkdir /app
WORKDIR /app

# Gradle Wrapper 및 build.gradle 파일 복사
COPY ./com.nameslowly.coinauctions.auth/gradlew /app/gradlew
COPY ./com.nameslowly.coinauctions.auth/gradlew.bat /app/gradlew.bat
COPY ./com.nameslowly.coinauctions.auth/build.gradle /app/build.gradle
COPY ./com.nameslowly.coinauctions.auth/gradle/ /app/gradle/
COPY ./com.nameslowly.coinauctions.auth/src/ /app/src/
RUN chmod +x /app/gradlew
# gralde 버젼 확인
RUN /app/gradlew --version

# common 모듈 복사
COPY ./com.nameslowly.coinauctions.common ./com.nameslowly.coinauctions.common

# settings.gradle 파일을 직접 생성 (common도 가져오게끔)
RUN echo "rootProject.name = 'app'" > settings.gradle
RUN echo "include 'com.nameslowly.coinauctions.common'" >> settings.gradle
# 만약 모듈이 해당 프로젝트 아래에 없을 경우엔 아래와 같이 위치를 정확히 명시해주어야함
RUN echo "project(':com.nameslowly.coinauctions.common').projectDir = file('com.nameslowly.coinauctions.common')" >> settings.gradle

# 필수 패키지 설치 (Debian 기반)
RUN apt-get update && apt-get install -y findutils

# gradlew 파일에 실행 권한 추가

# 프로젝트를 빌드
RUN chmod +x /app/gradlew
RUN /app/gradlew build

# 빌드된 JAR 파일을 임시 디렉토리로 복사
RUN cp /app/b\uild/libs/*.jar /app/
RUN ls

# 빌드된 결과를 실행
ENTRYPOINT ["java", "-jar", "/app/app-0.0.1-SNAPSHOT.jar"]
