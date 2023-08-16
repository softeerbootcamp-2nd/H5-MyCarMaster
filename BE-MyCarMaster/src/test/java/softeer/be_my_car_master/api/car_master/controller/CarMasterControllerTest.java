package softeer.be_my_car_master.api.car_master.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.api.car_master.dto.response.CarMasterDto;
import softeer.be_my_car_master.api.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.api.car_master.usecase.GetCarMasterUseCase;
import softeer.be_my_car_master.global.response.Response;

@WebMvcTest(CarMasterController.class)
@DisplayName("CarMaster Controller Test")
class CarMasterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetCarMasterUseCase getCarMasterUseCase;


	@Test
	@DisplayName("카마스터 목록 조회")
	void getInteriorColors() throws Exception {
		//given
		GetCarMasterResponse getCarMasterResponse = new GetCarMasterResponse();
		CarMasterDto carMasterDto = CarMasterDto.builder()
			.id(1L)
			.name("이몽룡")
			.intro("자기소개")
			.phone("010-0000-0000")
			.agency("한양대리점")
			.build();
		getCarMasterResponse.setCarMasters(Arrays.asList(carMasterDto));
		given(getCarMasterUseCase.execute(any(), any(), any())).willReturn(getCarMasterResponse);

		Response successResponse = Response.createSuccessResponse(getCarMasterResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/car-masters")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("latitude", "32.1231")
				.param("longitude", "127.32333")
				.param("filter", "SALES")
		);

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, true));
	}
}
