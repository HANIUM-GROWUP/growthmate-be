package com.growup.growthmate.company;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.growup.growthmate.company.dto.detail.CompanyDetailResponse;
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
    @DisplayName("기업 정보 조회")
    class CompanyInfoTest {

        @Test
        void 기업_정보_상세_조회한다() {

            //given
            CompanyDetailRequest request = new CompanyDetailRequest(COMPANY_ID);

            //when
            CompanyDetailResponse response = companyService.findCompanyDetail(request);

            //then
            assertAll(
                    () -> assertThat(response.name()).isEqualTo(NAME),
                    () -> assertThat(response.imageUrl()).isEqualTo(IMAGE_URL),
                    () -> assertThat(response.businessType()).isEqualTo(BUSINESSTYPE),
                    () -> assertThat(response.establishmentDate()).isEqualTo(ESTABLISHMENTDATE),
                    () -> assertThat(response.address()).isEqualTo(ADDRESS),
                    () -> assertThat(response.employeeNumber()).isEqualTo(EMPLOYMENTNUMBER),
                    () -> assertThat(response.sales()).isEqualTo(SALES)
            );
        }

    }

    @Nested
    @DisplayName("오각형 변수 조회")
    class CompanyAnalysisTest {

        @Test
        void 오각형_변수가_companyid_에_맞게_조회된다() {

            //given
            CompanyAnalysisRequest request = new CompanyAnalysisRequest(COMPANY_ID);

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
