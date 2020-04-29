CREATE TABLE tb_bus (
  code varchar(6) PRIMARY KEY,
  location point,
  line varchar(10) NOT NULL,
  FOREIGN KEY (line) REFERENCES tb_line(code)
);
