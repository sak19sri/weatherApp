package com.ps.weatherapp.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Entity
@Table(name = "WEATHER_STATUS")
public class WeatherStatus {

    @Id
    @GeneratedValue
    private String reportID;
    @Column(name = "CITY_NAME")
    private String cityName;
    @Column(name = "CURRENT_HITS")
    private Integer currentHits;
    @Column(name = "HISTORY_HITS")
    private Integer historyHits;

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCurrentHits() {
        return currentHits;
    }

    public void setCurrentHits(Integer currentHits) {
        this.currentHits = currentHits;
    }

    public Integer getHistoryHits() {
        return historyHits;
    }

    public void setHistoryHits(Integer historyHits) {
        this.historyHits = historyHits;
    }
}
