CREATE TABLE Vendors
   (
   VNum          NVARCHAR2(4),
   VendorName    NVARCHAR2(20),
   Region        INTEGER,
   HQLoc         NVARCHAR2(25),
   CONSTRAINT pk_Vendors PRIMARY KEY (VNum)
   );

INSERT INTO Vendors
   VALUES('V10', 'SnackMeister', 10, 'LosAngeles');
INSERT INTO Vendors	
   VALUES('V20', 'CostMore', 20, 'Seattle');
INSERT INTO Vendors	
   VALUES('V30' , 'ScamsClub', 30, 'Seattle');
INSERT INTO Vendors	
   VALUES('V40', 'Scamazon', 50, 'LosAngeles');
INSERT INTO Vendors	
   VALUES('V50', 'HomerClub', 30, 'Miami');
COMMIT;