SELECT 
    `name`,
    IF(HOUR(`start`) >= 0
            AND HOUR(`start`) < 12,
        'Morning',
        IF(HOUR(`start`) >= 12
                AND HOUR(`start`) < 18,
            'Afternoon',
            'Evening')) AS `Part of the Day`,
    IF(`duration` <= 3,
        'Extra Short',
        IF((`duration` > 3 AND `duration` <= 6),
            'Short',
            IF(`duration` > 6 AND `duration` <= 10,
                'Long',
                'Extra Long'))) AS `Duration`
FROM
    games;