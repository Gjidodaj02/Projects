CREATE OR REPLACE VIEW FINALQUERY3 AS 
--Get brands that ship none of the condiments that are above 30 calories per serving. 
SELECT BR.BRANDNAME
FROM XBRAND BR
WHERE BR.BRANDNUM IN
    (SELECT MB.BRANDNUM
    FROM XMADEBY MB
    WHERE MB.CONDNUM NOT IN
        (SELECT CON.CONDNUM
        FROM xCONDIMENT CON
        WHERE CON.CALPERSERV > 30));