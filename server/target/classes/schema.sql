CREATE TABLE IF NOT EXISTS WEATHER_STATUS (
        reportID INT,
        city_Name VARCHAR(20) NOT NULL,
        current_Hits INT DEFAULT 0,
        history_Hits INT DEFAULT 0
);

