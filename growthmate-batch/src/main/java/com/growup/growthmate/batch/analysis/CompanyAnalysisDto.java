package com.growup.growthmate.batch.analysis;

public record CompanyAnalysisDto(
        String name,
        int growth,
        int stability,
        int profitability,
        int efficiency,
        int businessPerformance
) {
}
