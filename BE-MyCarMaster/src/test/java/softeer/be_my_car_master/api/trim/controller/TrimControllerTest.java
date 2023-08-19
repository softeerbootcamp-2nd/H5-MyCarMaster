package softeer.be_my_car_master.api.trim.controller;

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

import softeer.be_my_car_master.api.option.usecase.GetTrimDefaultOptionsUseCase;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.api.trim.dto.response.TrimDefaultOptionDto;
import softeer.be_my_car_master.api.trim.dto.response.TrimDto;
import softeer.be_my_car_master.api.trim.usecase.get_trims.GetTrimsUseCase;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(TrimController.class)
@DisplayName("Trim Controller Test")
class TrimControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetTrimsUseCase getTrimsUseCase;
	@MockBean
	private GetTrimDefaultOptionsUseCase getTrimDefaultOptionsUseCase;

	private String getClientErrorResponseBody() throws JsonProcessingException {
		Response errorResponse = Response.createErrorResponse(ResponseStatus.BAD_REQUEST);
		String responseBody = objectMapper.writeValueAsString(errorResponse);
		return responseBody;
	}

	@Nested
	@DisplayName("getTrims Test")
	class GetTrimsTest {
		@Test
		@DisplayName("트림 목록을 조회합니다")
		void getTrims() throws Exception {
			//given
			GetTrimsResponse response = new GetTrimsResponse();
			TrimDto trimDto = TrimDto.builder()
				.id(1L)
				.name("Le Blanc")
				.description("Le Blanc Trim Description")
				.price(47720000)
				.ratio(22)
				.imgUrl("imgUrl")
				.build();
			response.setTrims(Arrays.asList(trimDto));

			given(getTrimsUseCase.execute(any())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/trims")
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
		void minimumModelId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/trims")
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
		void nonNullModelId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/trims")
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
	@DisplayName("getTrimDefaultOptions Test")
	class GetTrimDefaultOptionsTest {
		@Test
		@DisplayName("트림의 기본옵션 목록을 조회합니다")
		void getTrimDefaultOptions() throws Exception {
			//given
			GetTrimDefaultOptionsResponse response = new GetTrimDefaultOptionsResponse();
			TrimDefaultOptionDto trimDefaultOptionDto = TrimDefaultOptionDto.builder()
				.id(1L)
				.category(Category.SAFE.getValue())
				.name("HTRAC")
				.imgUrl("imgUrl")
				.description("옵션 상세설명")
				.build();
			response.setDefaultOptions(Arrays.asList(trimDefaultOptionDto));

			given(getTrimDefaultOptionsUseCase.execute(anyLong())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/trims/{trimId}/default-options", 1L)
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
