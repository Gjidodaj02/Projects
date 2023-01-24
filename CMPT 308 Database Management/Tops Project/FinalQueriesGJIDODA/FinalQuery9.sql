CREATE OR REPLACE VIEW FINALQUERY9 AS 
--Get condiments that are a celbrities favorite 
--whose local tops is in Lagrangeville. 
--Or can be found at a tops location in Lagrangeville.
SELECT CON.CONDNAME
FROM XCONDIMENT CON
WHERE CON.CONDNUM IN
    (SELECT CEL.FAVCOND
    FROM XCELEBRITY CEL
    WHERE CEL.LOCALTOPS = 'Lagrangeville')
OR CON.CONDNUM IN
    (SELECT FA.CONDNUM
    FROM XFOUNDAT FA
    WHERE FA.TOPSID IN
        (SELECT ST.TOPSID
        FROM XSTORE ST
        WHERE ST.CITY = 'Lagrangeville'));