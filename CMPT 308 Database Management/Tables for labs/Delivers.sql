CREATE TABLE Delivers
   (
      VNum      NVARCHAR2 (4),
      SNum      NVARCHAR2 (4),
      PNum      NVARCHAR2 (4),
      Amount    INTEGER,
      CONSTRAINT pk_Delivers PRIMARY KEY (VNum, SNum, PNum),
      CONSTRAINT fk_Delivers_Vendors FOREIGN KEY (VNum) REFERENCES Vendors(VNum),
      CONSTRAINT fk_Delivers_Snacks FOREIGN KEY (SNum) REFERENCES Snacks(SNum),
      CONSTRAINT fk_Delivers_Parks FOREIGN KEY (PNum) REFERENCES Parks(PNum)
   );
   
INSERT INTO Delivers
      VALUES('V10', 'S10', 'P100', 200);
INSERT INTO Delivers
      VALUES('V10', 'S10', 'P400', 700);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P100', 400);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P200', 200);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P300', 200);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P400', 500);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P500', 600);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P600', 400);
INSERT INTO Delivers      
        VALUES('V20', 'S30', 'P700', 800);
INSERT INTO Delivers      
        VALUES('V20', 'S50', 'P200', 100);
INSERT INTO Delivers      
        VALUES('V30', 'S30', 'P100', 200);
INSERT INTO Delivers      
        VALUES('V30', 'S40', 'P200', 500);
INSERT INTO Delivers      
        VALUES('V40', 'S60', 'P300', 300);
INSERT INTO Delivers      
        VALUES('V40', 'S60', 'P700', 300);
INSERT INTO Delivers      
        VALUES('V50', 'S10', 'P400', 100);
INSERT INTO Delivers      
        VALUES('V50', 'S20', 'P200', 200);
INSERT INTO Delivers      
        VALUES('V50', 'S20', 'P400', 100);
INSERT INTO Delivers      
        VALUES('V50', 'S30', 'P400', 200);
INSERT INTO Delivers      
        VALUES('V50', 'S40', 'P400', 800);
INSERT INTO Delivers      
        VALUES('V50', 'S50', 'P400', 400);
INSERT INTO Delivers      
        VALUES('V50', 'S50', 'P500', 500);
INSERT INTO Delivers      
        VALUES('V50', 'S50', 'P700', 100);
INSERT INTO Delivers      
        VALUES('V50', 'S60', 'P200', 200);
INSERT INTO Delivers      
        VALUES('V50', 'S60', 'P400', 500);
COMMIT;