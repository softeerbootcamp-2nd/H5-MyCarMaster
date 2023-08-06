package softeer.bemycarmaster.api.domain.trim.controller;

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

import softeer.bemycarmaster.api.domain.trim.dto.request.GetTrimsRequest;
import softeer.bemycarmaster.api.domain.trim.dto.response.GetTrimsResponse;
import softeer.bemycarmaster.api.domain.trim.dto.response.TrimDto;
import softeer.bemycarmaster.api.domain.trim.usecase.GetTrimsUseCase;
import softeer.bemycarmaster.api.global.response.Response;
import softeer.bemycarmaster.api.global.response.ResponseStatus;

@WebMvcTest(TrimController.class)
@DisplayName("Trim Controller Test")
class TrimControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetTrimsUseCase getTrimsUseCase;

	@Test
	@DisplayName("트림 목록을 조회합니다")
	void getTrims() throws Exception {
		//given
		String requestBody = getRequestBody(new GetTrimsRequest(1L));

		GetTrimsResponse getTrimsResponse = new GetTrimsResponse();
		TrimDto trimDto = TrimDto.builder()
			.id(1L)
			.name("Le Blanc")
			.description("Le Blanc Trim Description")
			.price(47720000)
			.ratio(22)
			.imgUrl("imgUrl")
			.build();
		getTrimsResponse.setTrims(Arrays.asList(trimDto));

		given(getTrimsUseCase.execute(any())).willReturn(getTrimsResponse);

		Response successResponse = Response.createSuccessResponse(getTrimsResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/trims")
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
		String requestBody = getRequestBody(new GetTrimsRequest(0L));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/trims")
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
		String requestBody = getRequestBody(new GetTrimsRequest(null));

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/trims")
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

	private String getRequestBody(GetTrimsRequest getTrimsRequest) throws
		JsonProcessingException {
		String requestBody = objectMapper.writeValueAsString(getTrimsRequest);
		return requestBody;
	}
}
