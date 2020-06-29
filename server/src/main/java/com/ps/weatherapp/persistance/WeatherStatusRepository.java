package com.ps.weatherapp.persistance;

import com.ps.weatherapp.entity.WeatherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This class acts as the database repository
 */
@Repository
public interface WeatherStatusRepository extends JpaRepository<WeatherStatus , Integer> {
    @Query(value = "Select w from WeatherStatus w  where w.cityName = :city_name")
    WeatherStatus findByCityName(@Param("city_name") String city_name);
}
