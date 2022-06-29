package com.careerdevs.weatherapi.repositories;

import com.careerdevs.weatherapi.models.CurrentWeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentReportRepository extends JpaRepository <CurrentWeatherReport, Long> {


}
