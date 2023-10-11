package com.growup.growthmate.company.mapper;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.find.CompanyDetailResponse;
import com.growup.growthmate.company.dto.find.SortedCompanyResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CompanyAnalysis.class
)
public interface CompanyMapper {

    @Mappings({
            @Mapping(source = "id", target = "companyId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "businessType", target = "businessType")
    })
    List<SortedCompanyResponse> toAllSelectDTO(List<Company> company);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "businessType", target = "businessType"),
            @Mapping(source = "establishmentDate", target = "establishmentDate"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "employeeNumber", target = "employeeNumber"),
            @Mapping(source = "sales", target = "sales")
    })
    CompanyDetailResponse toDetailDTO(Company company);

    @Mappings({
            @Mapping(source = "growth", target = "growth"),
            @Mapping(source = "stability", target = "stability"),
            @Mapping(source = "profitability", target = "profitability"),
            @Mapping(source = "efficiency", target = "efficiency"),
            @Mapping(source = "businessPerformance", target = "businessPerformance")
    })
    CompanyAnalysisResponse toAnalysisDTO(CompanyAnalysis companyAnalysis);

}
