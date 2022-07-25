-- DDL: Data Definition Language
DROP TABLE IF EXISTS jc_street;
DROP TABLE IF EXISTS jc_register_office;
DROP TABLE IF EXISTS jc_passport_office;
DROP TABLE IF EXISTS jc_country_struct;

CREATE TABLE jc_street
(
    street_code integer not null,
    street_name varchar(300),
    PRIMARY KEY(street_code)
);

-- 00 000 000 0000
-- xx 000 000 0000
-- 45 xxx 000 0000
-- 45 123 xxx 0000
-- 45 123 765 xxxx
-- 45 123 765 3456
CREATE TABLE jc_country_struct
(
    area_id char(12) not null,
    area_name varchar(200),
    PRIMARY KEY (area_id)
);

CREATE TABLE jc_passport_office
(
    p_office_id integer not null,
    p_area_id_fk char(12)not null,
    p_office_name varchar(200),
    PRIMARY KEY (p_office_id),
    FOREIGN KEY (p_area_id_fk) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE jc_register_office
(
    r_office_id integer not null,
    r_area_id_fk char(12)not null,
    r_office_name varchar(200),
    PRIMARY KEY (r_office_id),
    FOREIGN KEY (r_area_id_fk) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
)

-- DML: Data Manipulation Language