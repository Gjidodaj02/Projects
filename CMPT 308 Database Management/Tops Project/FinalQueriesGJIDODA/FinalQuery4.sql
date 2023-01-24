CREATE OR REPLACE VIEW FINALQUERY4 AS 
--Get condiments along with celebrities whose favorite condiment is that 
--condiment if any. (Get condiment and celebrity name)
SELECT CON.CONDNAME, CEL.CELEBNAME
FROM XCONDIMENT CON LEFT JOIN XCELEBRITY CEL ON CON.CONDNUM = CEL.FAVCOND;