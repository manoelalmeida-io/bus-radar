ALTER TABLE tb_bus ADD coordinate_x DECIMAL(10, 8);
ALTER TABLE tb_bus ADD coordinate_y DECIMAL(10, 8);

ALTER TABLE tb_bus DROP COLUMN location;