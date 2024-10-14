-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS auction;
CREATE DATABASE IF NOT EXISTS userAuth;
CREATE DATABASE IF NOT EXISTS bidwin;
CREATE DATABASE IF NOT EXISTS chat;
CREATE DATABASE IF NOT EXISTS coinpay;


# 계정 생성 및 권한부야
-- 데이터베이스 선택
-- 사용자 auth_user 생성
-- 사용자 권한 부여
-- 사용자 권한 변경 사항 적용

USE auction;
CREATE USER 'auction_user'@'%' IDENTIFIED BY 'auction_password';
GRANT ALL PRIVILEGES ON auction.* TO 'auction_user'@'%';
FLUSH PRIVILEGES;

USE userAuth;
CREATE USER 'auth_user'@'%' IDENTIFIED BY 'auth_password';
GRANT ALL PRIVILEGES ON userAuth.* TO 'auth_user'@'%';
FLUSH PRIVILEGES;

USE bidwin;
CREATE USER 'bidwin_user'@'%' IDENTIFIED BY 'bidwin_password';
GRANT ALL PRIVILEGES ON bidwin.* TO 'bidwin_user'@'%';
FLUSH PRIVILEGES;

USE chat;
CREATE USER 'chat_user'@'%' IDENTIFIED BY 'chat_password';
GRANT ALL PRIVILEGES ON chat.* TO 'chat_user'@'%';
FLUSH PRIVILEGES;

USE coinpay;
CREATE USER 'coinpay_user'@'%' IDENTIFIED BY 'coinpay_password';
GRANT ALL PRIVILEGES ON coinpay.* TO 'coinpay_user'@'%';
FLUSH PRIVILEGES;



# 배포위해서 테이블 및 예시 데이터들도 틈틈히 만들어주세요


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
INSERT INTO p_coins (coin_name, coin_real_name, coin_price, is_deleted)
VALUES ('WAVE', 'KRW-WAVES', 0, false),
       ('USDT', 'KRW-USDT', 0, false),
       ('CHZ', 'KRW-CHZ', 0, false);


CREATE TABLE IF NOT EXISTS p_user2 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- 사용자 이름은 고유해야 함
    password VARCHAR(100) NOT NULL,         -- 비밀번호
    email VARCHAR(100) NOT NULL UNIQUE,     -- 이메일 필드 추가
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일
);