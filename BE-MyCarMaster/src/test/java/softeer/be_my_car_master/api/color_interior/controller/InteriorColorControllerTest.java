package softeer.be_my_car_master.api.color_interior.controller;

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

import softeer.be_my_car_master.api.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.api.color_interior.dto.response.InteriorColorDto;
import softeer.be_my_car_master.api.color_interior.usecase.get_interior_colors.GetInteriorColorsUseCase;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(InteriorColorController.class)
@DisplayName("Interior Color Controller Test")
class InteriorColorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetInteriorColorsUseCase getInteriorColorsUseCase;

	@Test
	@DisplayName("내장 색상 목록을 조회합니다")
	void getInteriorColors() throws Exception {
		//given
		GetInteriorColorsResponse getInteriorColorsResponse = new GetInteriorColorsResponse();
		InteriorColorDto interiorColorDto = InteriorColorDto.builder()
			.id(1L)
			.name("Interior Color")
			.price(0)
			.ratio(32)
			.colorImgUrl("colorImgUrl")
			.coloredImgUrl("coloredImgUrl")
			.build();
		getInteriorColorsResponse.setInteriorColors(Arrays.asList(interiorColorDto));

		given(getInteriorColorsUseCase.execute(any(), any())).willReturn(getInteriorColorsResponse);

		Response successResponse = Response.createSuccessResponse(getInteriorColorsResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/interior-colors")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "1")
				.param("exteriorColorId", "1")
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
			get("/interior-colors")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "0")
				.param("exteriorColorId", "1")
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
			get("/interior-colors")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("exteriorColorId", "1")
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	@Test
	@DisplayName("ExteriorColorId는 1 이상이어야 합니다")
	void minimumExteriorColorId() throws Exception {
		//given
		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/interior-colors")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "1")
				.param("exteriorColorId", "0")
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	@Test
	@DisplayName("ExteriorColorId는 null값 일 수 없습니다")
	void nonNullExteriorColorId() throws Exception {
		//given
		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			get("/interior-colors")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "1")
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
