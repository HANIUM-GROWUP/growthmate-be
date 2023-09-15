package com.growup.growthmate.company.dto;

public record CompanyAnalysisResponse(Integer growth,
                                      Integer stability,
                                      Integer profitability,
                                      Integer efficiency,
                                      Integer businessPerformance) {
}
