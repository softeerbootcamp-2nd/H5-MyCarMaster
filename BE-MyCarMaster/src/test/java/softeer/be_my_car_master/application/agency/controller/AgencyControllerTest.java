package softeer.be_my_car_master.application.agency.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.application.agency.dto.response.AgencyInGuDto;
import softeer.be_my_car_master.application.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.application.agency.usecase.get_agencies.GetAgenciesUseCase;
import softeer.be_my_car_master.global.aspect.BindingAdvice;
import softeer.be_my_car_master.global.response.Response;

@WebMvcTest(AgencyController.class)
@EnableAspectJAutoProxy
@Import(BindingAdvice.class)
@DisplayName("Agency Controller Test")
class AgencyControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetAgenciesUseCase getAgenciesUseCase;

	@Test
	@DisplayName("대리점 목록을 조회합니다")
	void getAgencies() throws Exception {
		//given
		GetAgenciesResponse response = new GetAgenciesResponse();
		AgencyInGuDto agencyDto1 = AgencyInGuDto.builder()
			.id(1L)
			.name("한양대리점")
			.latitude(32.1212)
			.longitude(127.2323)
			.build();
		AgencyInGuDto agencyDto2 = AgencyInGuDto.builder()
			.id(2L)
			.name("성동대리점")
			.latitude(32.1212)
			.longitude(127.2323)
			.build();
		response.setAgencies(Arrays.asList(agencyDto1, agencyDto2));

		given(getAgenciesUseCase.execute(any())).willReturn(response);

		Response successResponse = Response.createSuccessResponse(response);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/agencies")
				.param("gu", "성동구")
		);

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, true));
	}
}
