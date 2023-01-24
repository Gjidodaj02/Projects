CREATE OR REPLACE VIEW TVJOINQUERY1 AS 
--Get all actors and their agents. (List the actors and agents' number and name) 
--Records: 37
SELECT ACTOR_NUM, ACTOR_NAME, AGENT_NUM, AGENT_NAME 
FROM ZACTOR NATURAL JOIN ZAGENT; 