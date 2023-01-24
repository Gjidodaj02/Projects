CREATE TABLE Snacks  
   (
      SNum          NVARCHAR2 (4),
      SnackName     NVARCHAR2 (20),
      Price         FLOAT,	
      Qty           INTEGER,
      SourceCity    NVARCHAR2 (20),
      CONSTRAINT pk_Snacks PRIMARY KEY (SNum)		
   );
	
INSERT INTO Snacks
   VALUES('S10', 'Pickles', 14.99, 120, 'LosAngeles');
INSERT INTO Snacks	
   VALUES('S20', 'Hamburgers', 24.99, 60, 'Seattle');
INSERT INTO Snacks	
   VALUES('S30', 'HotDogs', 19.99, 80, 'NewOrleans');
INSERT INTO Snacks	
   VALUES('S40', 'Chips', 14.99, 150, 'LosAngeles');
INSERT INTO Snacks	
   VALUES('S50', 'Nachos', 19.99, 100, 'Seattle');
INSERT INTO Snacks	
   VALUES('S60','Pretzels', 14.99, 40, 'LosAngeles');
COMMIT;