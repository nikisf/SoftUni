SELECT 
    peaks.peak_name,
    rivers.river_name,
    LOWER(CONCAT(LEFT(peaks.peak_name,
                        LENGTH(peaks.peak_name) - 1),
                    rivers.river_name)) AS Mix
FROM
    Peaks
        JOIN
    rivers ON RIGHT(peaks.peak_name, 1) = LEFT(rivers.river_name, 1)
ORDER BY mix;