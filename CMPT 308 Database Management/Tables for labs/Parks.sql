CREATE TABLE Parks
   (
      PNum             NVARCHAR2 (4),
      ParkName         NVARCHAR2 (20),
      Capacity         INTEGER,
      Location         NVARCHAR2 (25),
      CONSTRAINT pk_Parks PRIMARY KEY (PNum)
   );

INSERT INTO Parks
   VALUES('P100', 'OraclePark', 5500, 'Seattle');
INSERT INTO Parks
   VALUES('P200', 'MySQLPark', 20400, 'NewOrleans');
INSERT INTO Parks	
   VALUES('P300', 'MongoField', 5500, 'Miami');
INSERT INTO Parks	
   VALUES('P400', 'PostgreSQLPark', 13600, 'Miami');
INSERT INTO Parks	
   VALUES('P500', 'DB2Field', 8300, 'LosAngeles');
INSERT INTO Parks	
   VALUES('P600', 'AccessStadium', 6300, 'Poughkeepsie');
INSERT INTO Parks	
   VALUES('P700', 'TeradataField', 11100, 'LosAngeles');
COMMIT;