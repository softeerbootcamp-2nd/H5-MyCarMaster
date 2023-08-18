package softeer.be_my_car_master.api.estimate.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.api.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleBodyTypeDto;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleEngineDto;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleExteriorColorDto;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleInteriorColorDto;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleOptionDto;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleTrimDto;
import softeer.be_my_car_master.api.estimate.dto.response.SimpleWheelDriveDto;
import softeer.be_my_car_master.api.estimate.usecase.CreateEstimateUseCase;
import softeer.be_my_car_master.api.estimate.usecase.GetEstimateUseCase;
import softeer.be_my_car_master.global.response.Response;

@WebMvcTest(EstimateController.class)
@DisplayName("EstimateController Test")
public class EstimateControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CreateEstimateUseCase createEstimateUseCase;
	@MockBean
	private GetEstimateUseCase getEstimateUseCase;

	@Test
	@DisplayName("견적서를 조회합니다")
	void getEstimate() throws Exception {
		//given
		GetEstimateResponse getEstimateResponse = new GetEstimateResponse();
		SimpleTrimDto trim = SimpleTrimDto.builder()
			.name("트림")
			.price(10000)
			.build();
		SimpleEngineDto engine = SimpleEngineDto.builder()
			.name("엔진")
			.price(10000)
			.build();
		SimpleWheelDriveDto wheelDrive = SimpleWheelDriveDto.builder()
			.name("구동방식")
			.price(10000)
			.build();
		SimpleBodyTypeDto bodyType = SimpleBodyTypeDto.builder()
			.name("바디타입")
			.price(10000)
			.build();
		SimpleExteriorColorDto exteriorColor = SimpleExteriorColorDto.builder()
			.name("외장색상")
			.price(10000)
			.colorImgUrl("img url")
			.coloredImgUrl("img url")
			.build();
		SimpleInteriorColorDto interiorColor = SimpleInteriorColorDto.builder()
			.name("내장색상")
			.price(10000)
			.colorImgUrl("img url")
			.build();
		SimpleOptionDto option = SimpleOptionDto.builder()
			.name("옵션")
			.price(10000)
			.imgUrl("img url")
			.build();

		given(getEstimateUseCase.execute(any())).willReturn(getEstimateResponse);

		Response successResponse = Response.createSuccessResponse(getEstimateResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/estimates/{estimateId}", UUID.randomUUID())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		);

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, true));
	}
}
