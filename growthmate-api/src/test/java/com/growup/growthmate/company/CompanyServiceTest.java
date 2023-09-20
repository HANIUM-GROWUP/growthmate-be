package com.growup.growthmate.company;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.CompanyAnalysisResponse;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.growup.growthmate.fixture.CompanyFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/company_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Nested
    @DisplayName("오각형 변수 조회")
    class CompanyAnalysisTest {

        @Test
        void 오각형_변수가_companyid_에_맞게_조회된다() {

            //given
            CompanyAnalysisRequest request = new CompanyAnalysisRequest(COMPANY_ANALYSIS_ID);

            //when
            CompanyAnalysisResponse response = companyService.findCompanyAnalysis(request);

            //then
            assertAll(
                    () -> assertThat(response.growth()).isEqualTo(GROWTH),
                    () -> assertThat(response.stability()).isEqualTo(STABILITY),
                    () -> assertThat(response.profitability()).isEqualTo(PROFITABILITY),
                    () -> assertThat(response.efficiency()).isEqualTo(EFFICIENCY),
                    () -> assertThat(response.businessPerformance()).isEqualTo(BUISNESS_PERFORMANCE)
            );
        }

    }

}
