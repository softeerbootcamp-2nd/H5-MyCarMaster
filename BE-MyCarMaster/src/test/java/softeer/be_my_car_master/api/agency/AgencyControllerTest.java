package softeer.be_my_car_master.api.agency;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.api.agency.controller.AgencyController;
import softeer.be_my_car_master.api.agency.dto.response.AgencyDto;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.api.agency.usecase.GetAgenciesUseCase;
import softeer.be_my_car_master.global.response.Response;

@WebMvcTest(AgencyController.class)
@DisplayName("Agency Controller Test")
class AgencyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetAgenciesUseCase getAgenciesUseCase;

	@Test
	@DisplayName("대행사 목록을 조회합니다")
	void getBodyTypes() throws Exception {
		//given
		GetAgenciesResponse getAgenciesResponse = new GetAgenciesResponse();
		AgencyDto agencyDto1 = AgencyDto.builder()
			.name("성동구")
			.build();
		AgencyDto agencyDto2 = AgencyDto.builder()
			.name("마포구")
			.build();
		getAgenciesResponse.setAgencies(Arrays.asList(agencyDto1, agencyDto2));

		given(getAgenciesUseCase.execute()).willReturn(getAgenciesResponse);

		Response successResponse = Response.createSuccessResponse(getAgenciesResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(get("/agencies"));

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, true));
	}
}
