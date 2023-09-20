package com.growup.growthmate.company.mapper;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.CompanyAnalysisResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CompanyAnalysis.class
)
public interface CompanyAnalysisMapper {

    @Mappings({
            @Mapping(source = "growth", target = "growth"),
            @Mapping(source = "stability", target = "stability"),
            @Mapping(source = "profitability", target = "profitability"),
            @Mapping(source = "efficiency", target = "efficiency"),
            @Mapping(source = "businessPerformance", target = "businessPerformance")
    })
    CompanyAnalysisResponse toAnalysisDTO(CompanyAnalysis companyAnalysis);

}
