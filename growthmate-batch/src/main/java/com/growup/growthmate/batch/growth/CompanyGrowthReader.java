package com.growup.growthmate.batch.growth;

import com.growup.growthmate.batch.CompanyAbstractReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyGrowthReader extends CompanyAbstractReader<List<CompanyGrowthDto>> {

    public CompanyGrowthReader(@Value("${file-path.company-growth}") String path,
                               XSSRowToCompanyGrowthMapper mapper) {
        super(path, mapper);
    }
}
