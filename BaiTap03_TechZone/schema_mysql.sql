-- Tuy chon: Hibernate (ddl-auto=update) se tu tao bang nay khi chay app lan dau.
-- Neu muon tao san / seed du lieu mau thi chay script duoi day.

CREATE DATABASE IF NOT EXISTS techzone_db CHARACTER SET utf8mb4;
USE techzone_db;

CREATE TABLE IF NOT EXISTS categories (
    categoryid   INT AUTO_INCREMENT PRIMARY KEY,
    categoryname VARCHAR(255),
    images       VARCHAR(255),
    status       INT DEFAULT 1
);

INSERT INTO categories (categoryname, images, status) VALUES
('Laptop', 'https://picsum.photos/seed/laptop/300/200', 1),
('Điện thoại', 'https://picsum.photos/seed/phone/300/200', 1),
('Máy tính bảng', 'https://picsum.photos/seed/tablet/300/200', 1),
('Phụ kiện', 'https://picsum.photos/seed/accessory/300/200', 0),
('Màn hình', 'https://picsum.photos/seed/monitor/300/200', 1),
('Bàn phím - Chuột', 'https://picsum.photos/seed/keyboard/300/200', 1),
('Tai nghe', 'https://picsum.photos/seed/headphone/300/200', 1);
