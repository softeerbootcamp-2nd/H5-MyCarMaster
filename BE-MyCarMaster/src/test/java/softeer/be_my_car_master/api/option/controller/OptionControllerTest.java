package softeer.be_my_car_master.api.option.controller;

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

import softeer.be_my_car_master.api.option.dto.response.DefaultOptionDto;
import softeer.be_my_car_master.api.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.OptionDto;
import softeer.be_my_car_master.api.option.usecase.GetDefaultOptionsUseCase;
import softeer.be_my_car_master.api.option.usecase.GetOptionsUseCase;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(OptionController.class)
@DisplayName("OptionController Test")
class OptionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetOptionsUseCase getOptionsUseCase;
	@MockBean
	private GetDefaultOptionsUseCase getDefaultOptionsUseCase;

	@Nested
	@DisplayName("getOptions Test")
	class GetOptionsTest {
		@Test
		@DisplayName("선택 가능한 옵션 목록을 조회합니다")
		void getOptions() throws Exception {
			//given
			GetOptionsResponse getOptionsResponse = new GetOptionsResponse();
			getOptionsResponse.setExclusiveTags(Arrays.asList("N Performance"));
			OptionDto optionDto = OptionDto.builder()
				.id(1L)
				.name("어떤 옵션")
				.summary("옵션 요약")
				.description("옵션 상세설명")
				.price(0)
				.ratio(22)
				.imgUrl("imgUrl")
				.subOptions(null)
				.build();
			getOptionsResponse.setOptions(Arrays.asList(optionDto));

			given(getOptionsUseCase.execute(any(), any(), any(), any(), any())).willReturn(getOptionsResponse);

			Response successResponse = Response.createSuccessResponse(getOptionsResponse);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "0")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "0")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "0")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "0")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("interiorColorId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("interiorColorId는 1 이상이어야 합니다")
		void minimumInteriorColorId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
					.param("interiorColorId", "0")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}

		@Test
		@DisplayName("interiorColorId는 null값 일 수 없습니다")
		void nonNullInteriorColorId() throws Exception {
			//given
			String responseBody = getClientErrorResponseBody();

			//when
			ResultActions perform = mockMvc.perform(
				get("/options")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, false));
		}
	}

	@Nested
	@DisplayName("getDefaultOptions Test")
	class GetDefaultOptionsTest {
		@Test
		@DisplayName("기본 옵션 목록을 조회합니다")
		void getOptions() throws Exception {
			//given
			GetDefaultOptionsResponse getDefaultOptionsResponse = new GetDefaultOptionsResponse();
			DefaultOptionDto defaultOptionDto = DefaultOptionDto.builder()
				.id(1L)
				.name("어떤 옵션")
				.category(Category.SAFE)
				.description("옵션 상세설명")
				.imgUrl("imgUrl")
				.build();
			getDefaultOptionsResponse.setDefaultOptions(Arrays.asList(defaultOptionDto));

			given(getDefaultOptionsUseCase.execute(any(), any(), any(), any())).willReturn(getDefaultOptionsResponse);

			Response successResponse = Response.createSuccessResponse(getDefaultOptionsResponse);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "0")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "0")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "0")
					.param("bodyTypeId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("bodyTypeId", "1")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
					.param("bodyTypeId", "0")
			);

			//then
			perform
				.andExpect(status().is4xxClientError())
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
				get("/options/default")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("trimId", "1")
					.param("engineId", "1")
					.param("wheelDriveId", "1")
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