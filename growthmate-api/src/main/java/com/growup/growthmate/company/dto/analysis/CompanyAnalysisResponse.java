package com.growup.growthmate.company.dto.analysis;

public record CompanyAnalysisResponse(Integer growth,
                                      Integer stability,
                                      Integer profitability,
                                      Integer efficiency,
                                      Integer businessPerformance) {
}
