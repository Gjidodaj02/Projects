CREATE OR REPLACE VIEW TVJOINQUERY6 AS 
--Get all tv show and award names that share the same year if any for either.  
--Records: 125
SELECT A.AWARD_NAME, TVS.SHOW_NAME 
FROM ZAWARD A FULL JOIN ZTVSHOW TVS ON A.AWARD_YEAR = TVS.START_YEAR; 
