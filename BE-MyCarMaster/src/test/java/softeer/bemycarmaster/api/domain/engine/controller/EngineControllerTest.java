package softeer.bemycarmaster.api.domain.engine.controller;

import static org.mockito.ArgumentMatchers.*;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.bemycarmaster.api.domain.engine.dto.request.GetEnginesRequest;
import softeer.bemycarmaster.api.domain.engine.dto.response.EngineDto;
import softeer.bemycarmaster.api.domain.engine.dto.response.GetEnginesResponse;
import softeer.bemycarmaster.api.domain.engine.usecase.GetEnginesUseCase;
import softeer.bemycarmaster.api.global.response.Response;
import softeer.bemycarmaster.api.global.response.ResponseStatus;

@WebMvcTest(EngineController.class)
@DisplayName("Engine Controller Test")
class EngineControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetEnginesUseCase getEnginesUseCase;

	@Test
	@DisplayName("엔진 목록을 조회합니다")
	void getEngines() throws Exception {
		//given
		String requestBody = getRequestBody(new GetEnginesRequest(1L));

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
				.contentType("application/json")
				.content(requestBody)
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
		String requestBody = getRequestBody(new GetEnginesRequest(0L));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/engines")
				.contentType("application/json")
				.content(requestBody)
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
		String requestBody = getRequestBody(new GetEnginesRequest(null));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/engines")
				.contentType("application/json")
				.content(requestBody)
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	private String getClientErrorResponseBody() throws JsonProcessingException {
		Response errorResponse = Response.createErrorResponse(ResponseStatus.BAD_REQUEST);
		String responseBody = objectMapper.writeValueAsString(errorResponse);
		return responseBody;
	}

	private String getRequestBody(GetEnginesRequest getEnginesRequest) throws
		JsonProcessingException {
		String requestBody = objectMapper.writeValueAsString(getEnginesRequest);
		return requestBody;
	}
}
