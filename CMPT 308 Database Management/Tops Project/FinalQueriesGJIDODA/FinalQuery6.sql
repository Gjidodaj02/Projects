CREATE OR REPLACE VIEW FINALQUERY6 AS 
--Get brand along with awards they won if any and the rest of the awards. 
--(Get brand name, and award name and year) 
SELECT BR.BRANDNAME, AW.AWARDNAME, aw.awardyear
FROM XBRAND BR FULL JOIN XAWARD AW ON BR.BRANDNUM = AW.BRANDNUM;