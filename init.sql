-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS userAuth;

-- 데이터베이스 선택
USE userAuth;

-- 사용자 auth_user 생성
CREATE USER 'auth_user'@'%' IDENTIFIED BY 'auth_password';

-- 사용자 권한 부여
GRANT ALL PRIVILEGES ON userAuth.* TO 'auth_user'@'%';

-- 사용자 권한 변경 사항 적용
FLUSH PRIVILEGES;

-- p_user 테이블 생성
CREATE TABLE IF NOT EXISTS p_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- 사용자 이름은 고유해야 함
    password VARCHAR(100) NOT NULL         -- 비밀번호
);

-- 초기 데이터 삽입
INSERT INTO p_user (username, password) VALUES
    ('user1', 'password1'),
    ('user2', 'password2'),
    ('user3', 'password3');

CREATE TABLE IF NOT EXISTS p_user2 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- 사용자 이름은 고유해야 함
    password VARCHAR(100) NOT NULL,         -- 비밀번호
    email VARCHAR(100) NOT NULL UNIQUE,     -- 이메일 필드 추가
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일
);