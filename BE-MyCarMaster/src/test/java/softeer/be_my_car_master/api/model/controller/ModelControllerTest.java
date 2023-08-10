package softeer.be_my_car_master.api.model.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.api.model.dto.response.GetModelsResponse;
import softeer.be_my_car_master.api.model.dto.response.ModelDto;
import softeer.be_my_car_master.api.model.usecase.GetModelsUseCase;
import softeer.be_my_car_master.global.response.Response;

@WebMvcTest(ModelController.class)
@DisplayName("ModelController Test")
class ModelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetModelsUseCase getModelsUseCase;

	@Test
	@DisplayName("모델 목록을 조회합니다")
	void getModels() throws Exception {
		//given
		GetModelsResponse getModelsResponse = new GetModelsResponse();
		ModelDto modelDto = ModelDto.builder()
			.id(1L)
			.name("model name")
			.imgUrl("imgUrl")
			.build();
		getModelsResponse.setModels(Arrays.asList(modelDto));
		given(getModelsUseCase.execute()).willReturn(getModelsResponse);

		Response successResponse = Response.createSuccessResponse(getModelsResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(get("/models"));

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, true));
	}
}
