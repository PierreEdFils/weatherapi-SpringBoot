package com.careerdevs.weatherapi.repositories;

import com.careerdevs.weatherapi.models.ForecastReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastReportRepository extends JpaRepository<ForecastReport,Long> {
}
