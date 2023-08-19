package softeer.be_my_car_master.api.wheeldrive.controller;

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

import softeer.be_my_car_master.api.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.be_my_car_master.api.wheeldrive.dto.response.WheelDriveDto;
import softeer.be_my_car_master.api.wheeldrive.usecase.get_wheel_drives.GetWheelDrivesUseCase;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(WheelDriveController.class)
@DisplayName("WheelDrive Controller Test")
class WheelDriveControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetWheelDrivesUseCase getWheelDrivesUseCase;

	@Test
	@DisplayName("구동방식 목록을 조회합니다")
	void getWheelDrives() throws Exception {
		//given
		GetWheelDrivesResponse getWheelDrivesResponse = new GetWheelDrivesResponse();
		WheelDriveDto wheelDriveDto = WheelDriveDto.builder()
			.id(1L)
			.name("2WD")
			.description("2WD Description")
			.price(0)
			.ratio(22)
			.imgUrl("imgUrl")
			.build();
		getWheelDrivesResponse.setWheelDrives(Arrays.asList(wheelDriveDto));

		given(getWheelDrivesUseCase.execute(any(), any())).willReturn(getWheelDrivesResponse);

		Response successResponse = Response.createSuccessResponse(getWheelDrivesResponse);
		String responseBody = objectMapper.writeValueAsString(successResponse);

		//when
		ResultActions perform = mockMvc.perform(
			get("/wheel-drives")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "1")
				.param("engineId", "1")
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
			get("/wheel-drives")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "0")
				.param("engineId", "1")
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
			get("/wheel-drives")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("engineId", "1")
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
			get("/wheel-drives")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("trimId", "1")
				.param("engineId", "0")
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
			get("/wheel-drives")
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
