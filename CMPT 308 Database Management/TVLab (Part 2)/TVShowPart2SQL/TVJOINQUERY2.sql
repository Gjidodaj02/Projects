CREATE OR REPLACE VIEW TVJOINQUERY2 AS 
--Get all producers who earned an award (List producers' number, and name. Award name and year. 
--Records: 5
SELECT PROD_NUM, PROD_NAME, AWARD_NAME, AWARD_YEAR
FROM ZPRODUCER JOIN ZEARNS USING (PROD_NUM)
JOIN ZAWARD USING (AWARDID);
