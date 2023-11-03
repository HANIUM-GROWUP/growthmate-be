package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.batch.CompanyAbstractReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanySentimentReader extends CompanyAbstractReader<CompanySentimentDto> {

    public CompanySentimentReader(@Value("${file-path.company-sentiment}") String path,
                                  XSSRowToCompanySentimentMapper mapper) {
        super(path, mapper);
    }
}
