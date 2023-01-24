CREATE OR REPLACE VIEW FINALQUERY10 AS 
--Name ingredients that are used in at least one 
--condiment that can be found at a Tops location in Pennsylvania.
SELECT ING.MAINING
FROM XINGREDIENT ING
WHERE ING.INGNUM IN
    (SELECT CW.INGNUM
    FROM XCREATEDWITH CW
    WHERE CW.CONDNUM IN
        (SELECT CON.CONDNUM
        FROM XCONDIMENT CON
        WHERE CON.CONDNUM IN
            (SELECT FA.CONDNUM
            FROM XFOUNDAT FA
            WHERE FA.TOPSID IN
                (SELECT ST.TOPSID
                FROM XSTORE ST
                WHERE ST.STATE = 'Pennsylvania'))));