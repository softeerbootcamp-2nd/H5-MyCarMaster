package softeer.bemycarmaster.api.domain.color.exterior.controller;

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

import softeer.bemycarmaster.api.domain.color.exterior.dto.request.GetExteriorColorsRequest;
import softeer.bemycarmaster.api.domain.color.exterior.dto.response.ExteriorColorDto;
import softeer.bemycarmaster.api.domain.color.exterior.dto.response.GetExteriorColorsResponse;
import softeer.bemycarmaster.api.domain.color.exterior.usecase.GetExteriorColorsUseCase;
import softeer.bemycarmaster.api.global.response.Response;
import softeer.bemycarmaster.api.global.response.ResponseStatus;

@WebMvcTest(ExteriorColorController.class)
@DisplayName("Exterior Color Controller Test")
class ExteriorColorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetExteriorColorsUseCase getExteriorColorsUseCase;

	@Test
	@DisplayName("외장 색상 목록을 조회합니다")
	public void getExteriorColors() throws Exception {
		//given
		String requestBody = getRequestBody(new GetExteriorColorsRequest(1, 1));

		GetExteriorColorsResponse getExteriorColorsResponse = new GetExteriorColorsResponse();
		ExteriorColorDto exteriorColorDto = ExteriorColorDto.builder()
			.id(1)
			.name("Exterior Color")
			.price(0)
			.ratio(32)
			.colorImgUrl("colorImgUrl")
			.coloredImgUrl("coloredImgUrl")
			.build();
		getExteriorColorsResponse.setColors(Arrays.asList(exteriorColorDto));

		given(getExteriorColorsUseCase.execute(any(), any())).willReturn(getExteriorColorsResponse);

		Response successResponse = Response.createSuccessResponse(getExteriorColorsResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/colors/exterior")
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
	@DisplayName("modelId는 1 이상이어야 합니다")
	void minimumModelId() throws Exception {
		//given
		String requestBody = getRequestBody(new GetExteriorColorsRequest(0, 1));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/colors/exterior")
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
	@DisplayName("modelId는 null값 일 수 없습니다")
	void nonNullModelId() throws Exception {
		//given
		String requestBody = getRequestBody(new GetExteriorColorsRequest(null, 1));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/colors/exterior")
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
	@DisplayName("trimId는 1 이상이어야 합니다")
	void minimumTrimId() throws Exception {
		//given
		String requestBody = getRequestBody(new GetExteriorColorsRequest(1, 0));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/colors/exterior")
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
		String requestBody = getRequestBody(new GetExteriorColorsRequest(1, null));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/colors/exterior")
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

	private String getRequestBody(GetExteriorColorsRequest getExteriorColorsRequest) throws JsonProcessingException {
		String requestBody = objectMapper.writeValueAsString(getExteriorColorsRequest);
		return requestBody;
	}
}
