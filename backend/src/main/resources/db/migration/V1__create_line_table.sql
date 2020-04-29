CREATE TABLE tb_line (
  code varchar(10) PRIMARY KEY,
  name varchar(50) NOT NULL,
  forward varchar(50) NOT NULL,
  backward varchar(50) NOT NULL,
  color varchar(9) NOT NULL
);
