package softeer.be_my_car_master.api.consult;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import softeer.be_my_car_master.api.consult.controller.ConsultController;
import softeer.be_my_car_master.api.consult.dto.request.ApplyConsultingRequest;
import softeer.be_my_car_master.api.consult.dto.request.ClientDto;
import softeer.be_my_car_master.api.consult.usecase.apply_consulting.ApplyConsultingUseCase;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@WebMvcTest(ConsultController.class)
@DisplayName("Consult Controller Test")
class ConsultControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ApplyConsultingUseCase applyConsultingUseCase;

	@Test
	@DisplayName("카마스터에게 구매상담을 신청합니다.")
	void applyConsulting() throws Exception {
		//given
		ClientDto clientDto = new ClientDto("Hyundai", "Hyundai@email.com", "010-0000-0000");
		ApplyConsultingRequest request = new ApplyConsultingRequest(
			"62dd98f0-bd8e-11ed-93ab-325096b39f47", 1L, clientDto);

		String requestBody = objectMapper.writeValueAsString(request);
		willDoNothing().given(applyConsultingUseCase).execute(any(), any(), any(), any(), any());

		//when
		ResultActions perform = mockMvc.perform(
			post("/consultings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		//then
		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"));
	}

	@Test
	@DisplayName("estimateId는 uuid 형식이어야 합니다.")
	void uuidEstimateId() throws Exception {
		//given
		ClientDto clientDto = new ClientDto("Hyundai", "Hyundai@email.com", "010-0000-0000");
		ApplyConsultingRequest request = new ApplyConsultingRequest(
			"abcd-efgh-1234", 1L, clientDto);

		String requestBody = objectMapper.writeValueAsString(request);
		willDoNothing().given(applyConsultingUseCase).execute(any(), any(), any(), any(), any());

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			post("/consultings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	@Test
	@DisplayName("name은 null일 수 없습니다.")
	void nonNullName() throws Exception {
		//given
		ClientDto clientDto = new ClientDto(null, "Hyundai@email.com", "010-0000-0000");
		ApplyConsultingRequest request = new ApplyConsultingRequest(
			"62dd98f0-bd8e-11ed-93ab-325096b39f47", 1L, clientDto);

		String requestBody = objectMapper.writeValueAsString(request);
		willDoNothing().given(applyConsultingUseCase).execute(any(), any(), any(), any(), any());

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			post("/consultings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	@Test
	@DisplayName("잘못된 이메일 형식입니다.")
	void invalidEmailFormat() throws Exception {
		//given
		ClientDto clientDto = new ClientDto("Hyundai", "Hyundai!!email.com", "010-0000-0000");
		ApplyConsultingRequest request = new ApplyConsultingRequest(
			"62dd98f0-bd8e-11ed-93ab-325096b39f47", 1L, clientDto);

		String requestBody = objectMapper.writeValueAsString(request);
		willDoNothing().given(applyConsultingUseCase).execute(any(), any(), any(), any(), any());

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			post("/consultings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		);

		//then
		perform
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json(responseBody, false));
	}

	@Test
	@DisplayName("잘못된 번호 형식입니다.")
	void invalidPhoneFormat() throws Exception {
		//given
		ClientDto clientDto = new ClientDto("Hyundai", "Hyundai@email.com", "010-00000000");
		ApplyConsultingRequest request = new ApplyConsultingRequest(
			"62dd98f0-bd8e-11ed-93ab-325096b39f47", 1L, clientDto);

		String requestBody = objectMapper.writeValueAsString(request);
		willDoNothing().given(applyConsultingUseCase).execute(any(), any(), any(), any(), any());

		String responseBody = getClientErrorResponseBody();

		//when
		ResultActions perform = mockMvc.perform(
			post("/consultings")
				.contentType(MediaType.APPLICATION_JSON)
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
}
