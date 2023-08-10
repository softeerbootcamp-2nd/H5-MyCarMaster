package softeer.be_my_car_master.api.color_exterior.controller;

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

import softeer.be_my_car_master.api.color_exterior.dto.request.GetExteriorColorsRequest;
import softeer.be_my_car_master.api.color_exterior.dto.response.ExteriorColorDto;
import softeer.be_my_car_master.api.color_exterior.dto.response.GetExteriorColorsResponse;
import softeer.be_my_car_master.api.color_exterior.usecase.GetExteriorColorsUseCase;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

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
	void getExteriorColors() throws Exception {
		//given
		GetExteriorColorsResponse getExteriorColorsResponse = new GetExteriorColorsResponse();
		ExteriorColorDto exteriorColorDto = ExteriorColorDto.builder()
			.id(1L)
			.name("Exterior Color")
			.price(0)
			.ratio(32)
			.colorImgUrl("colorImgUrl")
			.coloredImgUrl("coloredImgUrl")
			.build();
		getExteriorColorsResponse.setColors(Arrays.asList(exteriorColorDto));

		given(getExteriorColorsUseCase.execute(any())).willReturn(getExteriorColorsResponse);

		Response successResponse = Response.createSuccessResponse(getExteriorColorsResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/exterior-colors")
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
			get("/exterior-colors")
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
			get("/exterior-colors")
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

	private String getRequestBody(GetExteriorColorsRequest getExteriorColorsRequest) throws JsonProcessingException {
		String requestBody = objectMapper.writeValueAsString(getExteriorColorsRequest);
		return requestBody;
	}
}
