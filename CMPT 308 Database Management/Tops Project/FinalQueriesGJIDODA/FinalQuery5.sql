CREATE OR REPLACE VIEW FINALQUERY5 AS 
--Get sauces along with condiments who have the same calories per serving if any. 
--(Get condiment and sauce name)
SELECT CON.CONDNAME, SAU.SAUCENAME
FROM XCONDIMENT CON RIGHT JOIN XSAUCE SAU ON CON.CALPERSERV = SAU.CALORIES;