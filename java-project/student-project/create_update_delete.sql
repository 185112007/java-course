-- DML: Data Manipulation Language

-- CLEAR ALL
DELETE FROM jc_street;

-- CREATE
-- insert single data
INSERT INTO jc_street (street_code, street_name) 
VALUES (1, 'Street First');

-- insert multiple data
INSERT INTO jc_street (street_code, street_name) 
VALUES 
    (2, 'Street Second'),
    (3, 'Street Third');

-- UPDATE
-- update all data for specific column
UPDATE jc_street 
SET street_name = 'Street Super';

-- update specific data for specific column
UPDATE jc_street 
SET street_name = 'Very Super'
WHERE street_code = 1;

-- update multiple column data  for specific column
UPDATE jc_street
SET street_code = 10, street_name = 'Tenth Street'
WHERE street_code = 1;

-- DELETE
-- delete specific data for specific table
DELETE FROM jc_street
WHERE street_code = 10;

-- delete all data for specific table
DELETE FROM jc_street;
