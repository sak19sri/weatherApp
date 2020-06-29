package com.ps.weatherapp.service;

import com.ps.weatherapp.constants.WeatherAppConstants;
import com.ps.weatherapp.controller.WeatherController;
import com.ps.weatherapp.entity.WeatherStatus;
import com.ps.weatherapp.models.CityDetails;
import com.ps.weatherapp.persistance.WeatherStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service Layer ,This class interacts with the db and controller
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherStatusRepository weatherStatusRepository;
    private static  final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    /**
     * This method provides analytics data
     * @return ,list of cities along with the data
     * @throws Exception
     */
    @Override
    public List<WeatherStatus> getCount() throws Exception{
        logger.info("Method to retrieve analytics data");
        List<WeatherStatus> weatherStatuses = weatherStatusRepository.findAll();
        return weatherStatuses;
    }

    /**
     * This method retrieves city and updates the record
     * @param cityDetails
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean saveCounts(CityDetails cityDetails) throws Exception {
        logger.info("To update hits for city {}",cityDetails.getCityName());
        boolean found =false;
        WeatherStatus weatherStatus = weatherStatusRepository.findByCityName(cityDetails.getCityName());

            if (weatherStatus != null) {
                int hits = 0;
                if (cityDetails.getTypeOfEnquiry().equalsIgnoreCase(WeatherAppConstants.WEATHER_CURRENT)) {
                    hits = weatherStatus.getCurrentHits();
                    weatherStatus.setCurrentHits(++hits);
                } else {
                    hits = weatherStatus.getHistoryHits();
                    weatherStatus.setHistoryHits(++hits);
                }
                System.out.println(weatherStatus.getCurrentHits());
                weatherStatusRepository.save(weatherStatus);
                found = true;
            }

            return found;
    }
}



