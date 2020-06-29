package com.ps.weatherapp.controller;


import com.ps.weatherapp.entity.WeatherStatus;
import com.ps.weatherapp.models.CityDetails;
import com.ps.weatherapp.service.WeatherReportService;
import com.ps.weatherapp.service.WeatherReportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * This class handles requests and return response to the front end.
 */
@RestController
@RequestMapping(path = "/weatherstatus")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {

    private static  final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
    private WeatherReportServiceImpl weatherReportService;

    /**
     * This method updates the value of hits
     * @param cityDetails , from frontend maps cityname and type of enquiry
     * @return
     */
    @PostMapping(path= "/save", consumes = "application/json")
    public HttpStatus save(@RequestBody CityDetails cityDetails){
        HttpStatus status = null;
        try{
            boolean found = weatherReportService.saveCounts(cityDetails);
            status = found ?HttpStatus.OK : HttpStatus.NOT_FOUND;
        }
        catch (Exception exc) {
            logger.error("Error while save method {} ", exc.getMessage());
        }
        return status;
    }

    /**
     * This method returns response to the frontend
     * @return
     */
    @GetMapping("/getAnalytics" )
    public ResponseEntity<List<WeatherStatus>> getAnalytics(){
        List<WeatherStatus> weatherStatusList=null;
        try {
             weatherStatusList = weatherReportService.getCount();
            if (weatherStatusList.size() > 0) {
                System.out.println("Database hit" + weatherStatusList);
            }
        }
        catch (Exception e){
            logger.error("Exception in Analytics {}" ,e.getMessage());
        }
        return new ResponseEntity<List<WeatherStatus>>(weatherStatusList, new HttpHeaders(), HttpStatus.OK);

    }



}
