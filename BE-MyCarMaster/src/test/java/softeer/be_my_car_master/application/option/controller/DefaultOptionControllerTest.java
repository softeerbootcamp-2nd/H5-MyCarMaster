package softeer.be_my_car_master.application.option.controller;

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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.application.option.dto.response.DefaultOptionDto;
import softeer.be_my_car_master.application.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.application.option.usecase.get_default_options.GetDefaultOptionsUseCase;
import softeer.be_my_car_master.application.option.usecase.get_trim_default_options.GetTrimDefaultOptionsUseCase;
import softeer.be_my_car_master.application.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.application.trim.dto.response.TrimDefaultOptionDto;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.global.aspect.BindingAdvice;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(DefaultOptionController.class)
@EnableAspectJAutoProxy
@Import(BindingAdvice.class)
@DisplayName("DefaultOptionController Test")
class DefaultOptionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetDefaultOptionsUseCase getDefaultOptionsUseCase;
	@MockBean
	private GetTrimDefaultOptionsUseCase getTrimDefaultOptionsUseCase;

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
					.contentType(MediaType.APPLICATION_FORM_URLENCODED));

			//then
			perform.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, true));
		}
	}

	@Nested
	@DisplayName("getDefaultOptions Test")
	class GetDefaultOptionsTest {

		@Test
		@DisplayName("기본 옵션 목록을 조회합니다")
		void getOptions() throws Exception {
			//given
			GetDefaultOptionsResponse response = new GetDefaultOptionsResponse();
			DefaultOptionDto defaultOptionDto = DefaultOptionDto.builder()
				.id(1L)
				.name("어떤 옵션")
				.category(Category.SAFE.getValue())
				.description("옵션 상세설명")
				.imgUrl("imgUrl")
				.build();
			response.setDefaultOptions(Arrays.asList(defaultOptionDto));

			given(getDefaultOptionsUseCase.execute(any(), any(), any(), any())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().isOk())
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
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "0")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
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
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("engineId는 1 이상이어야 합니다")
		void minimumEngineId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "0")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("engineId는 null값 일 수 없습니다")
		void nonNullEngineId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("wheelDriveId는 1 이상이어야 합니다")
		void minimumWheelDriveId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "0")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("wheelDriveId는 null값 일 수 없습니다")
		void nonNullWheelDriveId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("bodyTypeId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("bodyTypeId는 1 이상이어야 합니다")
		void minimumBodyTypeId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "0"));

			//then
			perform.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("bodyTypeId는 null값 일 수 없습니다")
		void nonNullBodyTypeId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1"));

			//then
			perform.andExpect(status().is4xxClientError())
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
