package softeer.be_my_car_master.application.car_master.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import softeer.be_my_car_master.application.agency.dto.response.CarMasterInAgencyDto;
import softeer.be_my_car_master.application.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.application.car_master.dto.response.AgencyDto;
import softeer.be_my_car_master.application.car_master.dto.response.CarMasterAgencyDto;
import softeer.be_my_car_master.application.car_master.dto.response.CarMasterDto;
import softeer.be_my_car_master.application.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.application.car_master.usecase.get_car_masters.GetCarMasterUseCase;
import softeer.be_my_car_master.application.car_master.usecase.get_car_masters_in_agency.GetCarMastersInAgencyUseCase;
import softeer.be_my_car_master.global.response.Response;

@WebMvcTest(CarMasterController.class)
@DisplayName("CarMaster Controller Test")
class CarMasterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetCarMasterUseCase getCarMasterUseCase;
	@MockBean
	private GetCarMastersInAgencyUseCase getCarMastersInAgencyUseCase;

	@Nested
	@DisplayName("getCarMaster Test")
	class GetCarMastersTest {

		@Test
		@DisplayName("카마스터 목록 조회")
		void getCarMasters() throws Exception {
			//given
			GetCarMasterResponse response = new GetCarMasterResponse();
			AgencyDto agencyDto = AgencyDto.builder()
				.id(1L)
				.name("한양대리점")
				.gu("성동구")
				.latitude(32.1212)
				.longitude(127.2133)
				.build();

			CarMasterAgencyDto carMasterAgencyDto = new CarMasterAgencyDto(agencyDto.getId(), agencyDto.getName());
			CarMasterDto carMasterDto = CarMasterDto.builder()
				.id(1L)
				.name("이몽룡")
				.intro("자기소개")
				.phone("010-0000-0000")
				.agency(carMasterAgencyDto)
				.build();

			response.setAgencies(Arrays.asList(agencyDto));
			response.setCarMasters(Arrays.asList(carMasterDto));
			given(getCarMasterUseCase.execute(any(), any())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/car-masters")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("latitude", "32.1231")
					.param("longitude", "127.32333")
			);

			//then
			perform
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, true));
		}
	}

	@Nested
	@DisplayName("getCarMasterInAgency Test")
	class GetCarMastersInAgencyTest {

		@Test
		@DisplayName("대리점에 속한 카마스터 목록을 조회합니다")
		void getCarMastersInAgency() throws Exception {
			//given
			GetCarMastersInAgencyResponse response = new GetCarMastersInAgencyResponse();
			CarMasterInAgencyDto carMasterInAgencyDto = CarMasterInAgencyDto.builder()
				.id(1L)
				.name("신짜오")
				.imgUrl("imgUrl")
				.intro("안녕하세요.")
				.phone("010-0000-0000")
				.build();

			response.setCarMasters(Arrays.asList(carMasterInAgencyDto));

			given(getCarMastersInAgencyUseCase.execute(any())).willReturn(response);

			Response successResponse = Response.createSuccessResponse(response);
			String responseBody = objectMapper.writeValueAsString(successResponse);

			//when
			ResultActions perform = mockMvc.perform(
				get("/agencies/1/car-masters")
			);

			//then
			perform
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(responseBody, true));
		}
	}
}
