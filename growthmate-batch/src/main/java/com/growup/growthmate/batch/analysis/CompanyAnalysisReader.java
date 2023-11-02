package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.CompanyAbstractReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyAnalysisReader extends CompanyAbstractReader<CompanyAnalysisDto> {

    public CompanyAnalysisReader(@Value("${file-path.company-analysis}") String path,
                                 XSSRowToCompanyAnalysisMapper mapper) {
        super(path, mapper);
    }
}
