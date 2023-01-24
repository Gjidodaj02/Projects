CREATE OR REPLACE VIEW TVJOINQUERY4 AS 
--Get all parents along with networks they have if any.  
--Records: 9
SELECT P.PARENT_NAME, N.NETWORK_ID 
FROM ZPARENTCOMP P LEFT JOIN ZNETWORK N ON P.PARENT_NUM = N.PARENT_NUM; 