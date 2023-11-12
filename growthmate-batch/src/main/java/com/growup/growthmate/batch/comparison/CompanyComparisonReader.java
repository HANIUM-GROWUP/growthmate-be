package com.growup.growthmate.batch.comparison;

import com.growup.growthmate.batch.CompanyAbstractReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyComparisonReader extends CompanyAbstractReader<CompanyComparisonDto> {

    protected CompanyComparisonReader(@Value("${file-path.company-comparison}") String path,
                                      XSSRowToCompanyComparisonMapper mapper) {
        super(path, mapper);
    }
}
