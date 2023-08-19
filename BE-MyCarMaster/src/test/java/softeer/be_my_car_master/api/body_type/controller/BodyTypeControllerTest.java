package softeer.be_my_car_master.api.body_type.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.api.body_type.dto.response.BodyTypeDto;
import softeer.be_my_car_master.api.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.api.body_type.usecase.get_body_types.GetBodyTypesUseCase;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(BodyTypeController.class)
@DisplayName("BodyType Controller Test")
class BodyTypeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetBodyTypesUseCase getBodyTypesUseCase;

	@Test
	@DisplayName("바디 타입 목록을 조회합니다")
	void getBodyTypes() throws Exception {
		//given
		GetBodyTypesResponse getBodyTypesResponse = new GetBodyTypesResponse();
		BodyTypeDto bodyTypeDto = BodyTypeDto.builder()
			.id(1L)
			.name("7인승")
			.description("7인승 Description")
			.price(0)
			.ratio(22)
			.imgUrl("imgUrl")
			.build();
		getBodyTypesResponse.setBodyTypes(Arrays.asList(bodyTypeDto));

		given(getBodyTypesUseCase.execute(any())).willReturn(getBodyTypesResponse);

		Response successResponse = Response.createSuccessResponse(getBodyTypesResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/body-types")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("modelId", "1")
		);

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, true));
	}

	@Test
	@DisplayName("modelId는 1 이상이어야 합니다")
	void minimumTrimId() throws Exception {
		//given
		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/body-types")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("modelId", "0")
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	@Test
	@DisplayName("modelId는 null값 일 수 없습니다")
	void nonNullTrimId() throws Exception {
		//given
		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/body-types")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
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
}
