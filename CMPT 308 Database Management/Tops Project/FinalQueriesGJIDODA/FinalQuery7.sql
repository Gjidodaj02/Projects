CREATE OR REPLACE VIEW FINALQUERY7 AS 
--Get condiments that are sold by a brand that has won an award, 
--have another flavor and is a favorite of one of the celebrities.
SELECT CON.CONDNAME
FROM XCONDIMENT CON
WHERE CON.CONDNUM IN
    (SELECT MB.CONDNUM
    FROM XMADEBY MB
    WHERE MB.BRANDNUM IN
        (SELECT BR.BRANDNUM
        FROM XBRAND BR
        WHERE BR.BRANDNUM IN
            (SELECT AW.BRANDNUM
            FROM XAWARD AW)))
AND CON.CONDNUM IN
    (SELECT FL.FLAVORNUM
    FROM XFLAVOR FL)
AND CON.CONDNUM IN
    (SELECT CEL.FAVCOND
    FROM XCELEBRITY CEL);