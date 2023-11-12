package com.growup.growthmate.batch.comparison;

public record CompanyComparisonDto(
        String name,
        Double salesForecastPercentage,
        Long salesForecast
) {
}
