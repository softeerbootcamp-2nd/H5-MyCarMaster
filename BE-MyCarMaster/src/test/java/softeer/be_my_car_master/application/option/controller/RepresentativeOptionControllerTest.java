package softeer.be_my_car_master.application.option.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

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

import softeer.be_my_car_master.application.option.dto.response.AppliedOptionDto;
import softeer.be_my_car_master.application.option.dto.response.FilterDto;
import softeer.be_my_car_master.application.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.application.option.dto.response.RepresentativeOptionDto;
import softeer.be_my_car_master.application.option.usecase.get_representative_options.GetRepresentativeOptionsUseCase;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.exception.BindingAdvice;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(RepresentativeOptionController.class)
@EnableAspectJAutoProxy
@Import(BindingAdvice.class)
@DisplayName("RepresentativeOptionController Test")
class RepresentativeOptionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetRepresentativeOptionsUseCase getRepresentativeOptionsUseCase;

	@Nested
	@DisplayName("getRepresentativeOptions Test")
	class GetRepresentativeOptionsTest {

		@Test
		@DisplayName("모델의 대표 옵션 9개를 조회합니다")
		void getOptions() throws Exception {
			//given
			GetRepresentativeOptionsResponse response = new GetRepresentativeOptionsResponse();
			FilterDto filterDto = FilterDto.from(List.of(1L, 2L, 3L, 4L), List.of(2L, 3L), List.of(4L));
			Option appliedOption = Option.builder()
				.id(1L)
				.name("어떤 옵션")
				.price(10000)
				.category(Category.SAFE)
				.imgUrl("imgUrl")
				.build();
			AppliedOptionDto appliedOptionDto = AppliedOptionDto.from(appliedOption);
			RepresentativeOptionDto representativeOptionDto = RepresentativeOptionDto.builder()
				.id(1L)
				.name("어떤 옵션")
				.summary("옵션 요약")
				.description("옵션 상세설명")
				.imgUrl("imgUrl")
				.subOptions(null)
				.filter(filterDto)
				.appliedOption(appliedOptionDto)
				.build();
			response.setRepresentativeOptions(Arrays.asList(representativeOptionDto));

			given(getRepresentativeOptionsUseCase.execute(any())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/options/representative")
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
				get("/options/representative")
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
				get("/options/representative")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
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
