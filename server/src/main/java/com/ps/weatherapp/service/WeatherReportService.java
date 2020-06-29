package com.ps.weatherapp.service;

import com.ps.weatherapp.entity.WeatherStatus;
import com.ps.weatherapp.models.CityDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherReportService {

    List<WeatherStatus> getCount() throws Exception;
    boolean saveCounts(CityDetails cityDetails) throws Exception;
}
