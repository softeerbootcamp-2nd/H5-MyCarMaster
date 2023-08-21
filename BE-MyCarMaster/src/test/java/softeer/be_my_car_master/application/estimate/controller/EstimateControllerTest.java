package softeer.be_my_car_master.application.estimate.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.application.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.application.estimate.dto.request.EstimateOptionRequestDto;
import softeer.be_my_car_master.application.estimate.dto.response.CreateEstimateResponse;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateBodyTypeDto;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateEngineDto;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateExteriorColorDto;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateInteriorColorDto;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateOptionResponseDto;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateTrimDto;
import softeer.be_my_car_master.application.estimate.dto.response.EstimateWheelDriveDto;
import softeer.be_my_car_master.application.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.application.estimate.usecase.create_estimate.CreateEstimateUseCase;
import softeer.be_my_car_master.application.estimate.usecase.get_estimate.GetEstimateUseCase;
import softeer.be_my_car_master.domain.option.Category;
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

	@Nested
	@DisplayName("createEstimate Test")
	class CreateEstimateTest {

		@Test
		@DisplayName("견적서를 생성합니다")
		void createEstimate() throws Exception {
			// given
			EstimateOptionRequestDto estimateOptionRequestDto1 = new EstimateOptionRequestDto(1L, 500);
			EstimateOptionRequestDto estimateOptionRequestDto2 = new EstimateOptionRequestDto(2L, 500);
			EstimateOptionRequestDto estimateOptionRequestDto3 = new EstimateOptionRequestDto(5L, 500);
			EstimateOptionRequestDto estimateOptionRequestDto4 = new EstimateOptionRequestDto(6L, 500);

			CreateEstimateRequest request = CreateEstimateRequest.builder()
				.modelId(1L)
				.trimId(1L)
				.trimPrice(500)
				.engineId(1L)
				.enginePrice(500)
				.wheelDriveId(1L)
				.wheelDrivePrice(500)
				.bodyTypeId(1L)
				.bodyTypePrice(500)
				.exteriorColorId(1L)
				.exteriorColorPrice(500)
				.interiorColorId(1L)
				.interiorColorPrice(500)
				.selectOptions(Arrays.asList(estimateOptionRequestDto1, estimateOptionRequestDto2))
				.selectOptionPrice(1000)
				.considerOptions(Arrays.asList(estimateOptionRequestDto3, estimateOptionRequestDto4))
				.totalPrice(4000)
				.build();
			String requestBody = objectMapper.writeValueAsString(request);

			CreateEstimateResponse response
				= new CreateEstimateResponse(UUID.fromString("62dd98f0-bd8e-11ed-93ab-325096b39f47"));

			given(createEstimateUseCase.execute(any())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				post("/estimates")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody)
			);

			//then
			perform
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, true));
		}
	}

	@Nested
	@DisplayName("getEstimate Test")
	class GetEstimateTest {

		@Test
		@DisplayName("견적서를 조회합니다")
		void getEstimate() throws Exception {
			//given
			GetEstimateResponse getEstimateResponse = new GetEstimateResponse();
			EstimateTrimDto trim = EstimateTrimDto.builder()
				.name("트림")
				.price(10000)
				.build();
			EstimateEngineDto engine = EstimateEngineDto.builder()
				.name("엔진")
				.price(10000)
				.build();
			EstimateWheelDriveDto wheelDrive = EstimateWheelDriveDto.builder()
				.name("구동방식")
				.price(10000)
				.build();
			EstimateBodyTypeDto bodyType = EstimateBodyTypeDto.builder()
				.name("바디타입")
				.price(10000)
				.build();
			EstimateExteriorColorDto exteriorColor = EstimateExteriorColorDto.builder()
				.name("외장색상")
				.price(10000)
				.colorImgUrl("img url")
				.coloredImgUrl("img url")
				.build();
			EstimateInteriorColorDto interiorColor = EstimateInteriorColorDto.builder()
				.name("내장색상")
				.price(10000)
				.colorImgUrl("img url")
				.build();
			EstimateOptionResponseDto option = EstimateOptionResponseDto.builder()
				.name("옵션")
				.price(10000)
				.imgUrl("img url")
				.category(Category.SAFE.getValue())
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
}
