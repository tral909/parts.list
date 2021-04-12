DROP DATABASE IF EXISTS test;
CREATE DATABASE IF NOT EXISTS test CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS test.part (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  required    BIT NOT NULL DEFAULT 0,
  amount      INT NOT NULL
) ENGINE = InnoDB;

INSERT INTO test.part VALUES (1, 'Motherboard', 1, 3);
INSERT INTO test.part VALUES (2, 'Soundboard', 0, 2);
INSERT INTO test.part VALUES (3, 'Network adapter', 0, 5);
INSERT INTO test.part VALUES (4, 'CPU', 1, 7);
INSERT INTO test.part VALUES (5, 'RAM', 1, 4);
INSERT INTO test.part VALUES (6, 'Videocard', 1, 2);
INSERT INTO test.part VALUES (7, 'External CD-ROM', 0, 3);
INSERT INTO test.part VALUES (8, 'Printer', 0, 3);
INSERT INTO test.part VALUES (9, 'Soundboard', 0, 2);
INSERT INTO test.part VALUES (10, 'Cooler', 1, 4);
INSERT INTO test.part VALUES (11, 'Mouse', 0, 8);
INSERT INTO test.part VALUES (12, 'Keyboard', 0, 5);
INSERT INTO test.part VALUES (13, 'Monitor', 1, 5);
INSERT INTO test.part VALUES (14, 'Loudspeakers', 0, 12);
INSERT INTO test.part VALUES (15, 'CD-ROM', 0, 7);
INSERT INTO test.part VALUES (16, 'Carpet for mouse', 0, 2);
INSERT INTO test.part VALUES (17, 'Gamepad', 0, 3);
INSERT INTO test.part VALUES (18, 'Computer table', 0, 1);
INSERT INTO test.part VALUES (19, 'Flash card 4Gb', 0, 15);
INSERT INTO test.part VALUES (20, 'Flash card 8Gb', 0, 12);
INSERT INTO test.part VALUES (21, 'Flash card 16Gb', 0, 10);
INSERT INTO test.part VALUES (22, 'Flash card 32Gb', 0, 8);
INSERT INTO test.part VALUES (23, 'HDD 128Gb', 1, 8);
INSERT INTO test.part VALUES (24, 'HDD 512Gb', 1, 6);
INSERT INTO test.part VALUES (25, 'HDD 1024Gb', 1, 5);
INSERT INTO test.part VALUES (26, 'SSD 128Gb', 0, 5);
INSERT INTO test.part VALUES (27, 'SSD 256Gb', 0, 4);
INSERT INTO test.part VALUES (28, 'SSD 512Gb', 0, 2);
INSERT INTO test.part VALUES (29, 'Modem', 0, 4);
INSERT INTO test.part VALUES (30, 'WiFi router', 0, 5);
INSERT INTO test.part VALUES (31, 'Switch 20 ports', 0, 6);
INSERT INTO test.part VALUES (32, 'Switch 40 ports', 0, 3);
INSERT INTO test.part VALUES (33, 'Twisted pair 1m', 0, 12);
INSERT INTO test.part VALUES (34, 'Twisted pair 2m', 0, 11);
INSERT INTO test.part VALUES (35, 'Twisted pair 5m', 0, 8);
INSERT INTO test.part VALUES (36, 'Twinaxial cable 1m', 0, 6);
INSERT INTO test.part VALUES (37, 'Twinaxial cable 2m', 0, 5);
INSERT INTO test.part VALUES (38, 'Twinaxial cable 5m', 0, 2);