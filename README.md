## 🙌 Coin Auction
코인으로 거래하는 실시간 경매 서비스


## ❓프로젝트 소개
- 다양한 중고 물품을 경매에 등록하고 입찰할 수 있는 플랫폼으로, 입찰과 결제는 암호화폐(코인)로 이루어집니다.
- 자원을 재활용하는 동시에 암호화폐의 새로운 거래처를 제공합니다.
- 이를 통해 환경 보호와 함께 암호화폐의 실용성을 높이는 긍정적인 효과를 기대해봅니다.


## 🙋‍♀️ MSA 서비스 개요
- 🔐 **유저 및 인증인가**: 기본값인 사용자(USER), 관리자(MASTER), 게스트(GUEST)
- 🔐 **경매**: 경매 등록
- 🗂️ **입찰**: 입찰 등록 및 기존 입찰 취소
- 🗂️ **코인 및 결재**: 
- 🏪 **채팅**: 경매의 라이프사이클에 따른 채팅방의 생성과 종료


## 개발 인원 (4명 BE)
| 이름 | 역할 | 담당 |
| --- | --- | --- |
| 권판준 | 리더 | 경매 및 입찰 |
| 조성진 | 부리더 | 유저 및 인증, CI/CD |
| 고혁진 | 팀원 | 코인 및 결제 |
| 권민주 | 팀원 | 채팅 |


## ⚙ 기술 스택
#### 공통
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> 
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
<img src="https://img.shields.io/badge/Spring Cloud-6DB33F?style=for-the-badge&logo=Spring cloud&logoColor=white">

<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-
badge&logo=Hibernate&logoColor=white">
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=RabbitMQ&logoColor=white"> 

#### 데이터베이스
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=Redis&logoColor=white">

#### 채팅 (프론트 포함)
<img src="https://img.shields.io/badge/AmazonS3-569A31?style=for-the-badge&logo=AmazonS3&logoColor=white"> 
<img src="https://img.shields.io/badge/websocket-569A31?style=for-the-badge&logo=websocket&logoColor=white"> 
<img src="https://img.shields.io/badge/STOMP-569A31?style=for-the-badge&logo=STOMP&logoColor=white"> 

<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">

#### 모니터링
<img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=Prometheus&logoColor=white"> 
<img src="https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=Grafana&logoColor=white"> 
<img src="https://img.shields.io/badge/Zipkin-FF6600?style=for-the-badge&logo=Zipkin&logoColor=white"> 


## 📄 ERD 다이어그램
<details>
  <summary>ERD 다이어그램</summary>
  ![image](https://github.com/user-attachments/assets/a7eb1675-6e82-42b1-8f7f-a0efeeb79fe4)
</details>


## 인프라 설계도
<details>
  <summary>인프라 설계도</summary>
  ![image](https://github.com/user-attachments/assets/851e4310-4a6b-41ca-8fa2-33fb077615a7)
</details>


## 시스템 구조도
<details>
  <summary>시스템 구조도 </summary>
  ![image](https://github.com/user-attachments/assets/51e64d48-6ebf-4e35-8f7f-11e316465992)
</details>


## 플로우 차트
<details>
  <summary>플로우 차트 </summary>
  ![image](https://github.com/user-attachments/assets/b44d4bdf-69d2-46bc-8058-a21beecba248)
  ![image](https://github.com/user-attachments/assets/da507196-653a-4969-8cae-ea5a83887cbb)
</details>


## 채팅 페이지
<details>
  <summary>채팅 페이지 </summary>
  ![image](https://github.com/user-attachments/assets/433d339c-426f-49c8-8bb5-ccc7a6d55415)
</details>



## 📄 서비스 구성 및 실행방법

### 필수 설치 사항
- Java 17.x : Spring Boot 애플리케이션을 실행하기 위한 JDK
- Docker : 애플리케이션을 컨테이너로 실행하기 위한 Docker
- MySQL 8.x : 백엔드 데이터베이스로 사용할 MySQL 서버

### 설치 및 실행 방법
- project 다운 및 Docker 설치
- 터미널을 실행하여 project root로 이동
- 터미널에서 docker compose -f db-compose.yml up --build -d 입력
- 터미널에서 docker compose -f app-compse.yml up --build -d 입력
- Gateway(localhost:8080)를 통해 api 호출
- API 문서 : https://teamsparta.notion.site/API-2212243ea8ad4f1597acb2bb72a11cf6


## 트러블 슈팅
