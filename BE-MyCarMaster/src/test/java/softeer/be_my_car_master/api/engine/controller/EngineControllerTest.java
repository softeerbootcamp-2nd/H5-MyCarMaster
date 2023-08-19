package softeer.be_my_car_master.api.engine.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.api.engine.dto.response.EngineDto;
import softeer.be_my_car_master.api.engine.dto.response.GetEnginesResponse;
import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.api.engine.dto.response.UnselectableOptionDto;
import softeer.be_my_car_master.api.engine.usecase.GetUnselectableOptionsByEngineUseCase;
import softeer.be_my_car_master.api.engine.usecase.get_engines.GetEnginesUseCase;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(EngineController.class)
@DisplayName("EngineController Test")
class EngineControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetEnginesUseCase getEnginesUseCase;
	@MockBean
	private GetUnselectableOptionsByEngineUseCase getUnselectableOptionsByEngineUseCase;

	@Nested
	@DisplayName("getEngines Test")
	class GetEnginesTest {
		@Test
		@DisplayName("엔진 목록을 조회합니다")
		void getEngines() throws Exception {
			//given
			GetEnginesResponse getEnginesResponse = new GetEnginesResponse();
			EngineDto engineDto = EngineDto.builder()
				.id(1L)
				.name("가솔린 3.8")
				.description("가솔린 3.8 Description")
				.price(0)
				.ratio(22)
				.imgUrl("imgUrl")
				.power(295)
				.toque(36.2)
				.fuelMin(8.0)
				.fuelMax(9.2)
				.build();
			getEnginesResponse.setEngines(Arrays.asList(engineDto));

			given(getEnginesUseCase.execute(any())).willReturn(getEnginesResponse);

			Response successResponse = Response.createSuccessResponse(getEnginesResponse);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
			);

			//then
			perform
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, true));
		}

		@Test
		@DisplayName("trimId는 1 이상이어야 합니다")
		void minimumTrimId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "0")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("trimId는 null값 일 수 없습니다")
		void nonNullTrimId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}
	}

	@Nested
	@DisplayName("getUnselectableOptionsByEngine Test")
	class GetUnselectableOptionsByEngineTest {
		@Test
		@DisplayName("변경하려는 엔진에 따라 선택불가능해지는 옵션 목록을 조회합니다")
		void getUnselectableOptionsByEngine() throws Exception {
			//given
			GetUnselectableOptionsByEngineResponse getUnselectableOptionsByEngineResponse =
				new GetUnselectableOptionsByEngineResponse();
			UnselectableOptionDto unselectableOptionDto = UnselectableOptionDto.builder()
				.id(1L)
				.name("주차보조 시스템||")
				.price(1280000)
				.build();
			getUnselectableOptionsByEngineResponse.setUnselectableOptions(Arrays.asList(unselectableOptionDto));

			given(getUnselectableOptionsByEngineUseCase.execute(any(), any(), any())).willReturn(
				getUnselectableOptionsByEngineResponse);

			Response successResponse = Response.createSuccessResponse(getUnselectableOptionsByEngineResponse);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines/1/unselectable-options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("optionIds", "1,2")
			);

			//then
			perform
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, true));
		}

		@Test
		@DisplayName("trimId는 1 이상이어야 합니다")
		void minimumTrimId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines/1/unselectable-options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "0")
					.param("optionIds", "1,2")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("trimId는 null값 일 수 없습니다")
		void nonNullTrimId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines/1/unselectable-options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("optionIds", "1,2")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("optionIds는 null값 일 수 없습니다")
		void nonNullOptionIds() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines/1/unselectable-options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("optionIds는 빈 List일 수 없습니다")
		void nonEmptyOptionIds() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/engines/1/unselectable-options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("optionIds", "")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}
	}

	private String getClientErrorResponseBody() throws JsonProcessingException {
		Response errorResponse = Response.createErrorResponse(ResponseStatus.BAD_REQUEST);
		String responseBody = objectMapper.writeValueAsString(errorResponse);
		return responseBody;
	}

}
