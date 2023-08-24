package softeer.be_my_car_master.application.estimate.usecase.create_estimate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import softeer.be_my_car_master.application.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.application.estimate.dto.request.EstimateOptionRequestDto;
import softeer.be_my_car_master.application.estimate.dto.response.CreateEstimateResponse;
import softeer.be_my_car_master.application.estimate.exception.InvalidEstimationException;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.domain.model.Type;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateEstimateUseCase Test")
class CreateEstimateUseCaseTest {

	@InjectMocks
	private CreateEstimateUseCase useCase;

	@Mock
	private CreateEstimatePort port;

	@Test
	@DisplayName("견적서를 생성합니다")
	void execute() {
		// given
		EstimateOptionRequestDto estimateOptionRequestDto1 = new EstimateOptionRequestDto(1L, 500);
		EstimateOptionRequestDto estimateOptionRequestDto2 = new EstimateOptionRequestDto(2L, 500);
		EstimateOptionRequestDto estimateOptionRequestDto3 = new EstimateOptionRequestDto(5L, 500);
		EstimateOptionRequestDto estimateOptionRequestDto4 = new EstimateOptionRequestDto(6L, 500);

		CreateEstimateRequest request = CreateEstimateRequest.builder()
			.modelId(1L)
			.trimId(1L)
			.trimPrice(500)
			.engineId(1L)
			.enginePrice(500)
			.wheelDriveId(1L)
			.wheelDrivePrice(500)
			.bodyTypeId(1L)
			.bodyTypePrice(500)
			.exteriorColorId(1L)
			.exteriorColorPrice(500)
			.interiorColorId(1L)
			.interiorColorPrice(500)
			.selectOptions(Arrays.asList(estimateOptionRequestDto1, estimateOptionRequestDto2))
			.selectOptionPrice(1000)
			.considerOptions(Arrays.asList(estimateOptionRequestDto3, estimateOptionRequestDto4))
			.totalPrice(4000)
			.build();

		given(port.findModels()).willReturn(Arrays.asList(
			new Model(1L, "model", "url", 100, Type.SUV)
			));
		given(port.findTrimsByModel(any()))
			.willReturn(Arrays.asList(
				new Trim(1L, "model", "description", 22, 500, "url")
			));
		given(port.findEnginesByTrim(any()))
			.willReturn(Arrays.asList(
				new Engine(1L, "engine", "description", 25, 500, "url",
					22.0, 33.4, 22, 45.5)
			));
		given(port.findBodyTypesByModel(any()))
			.willReturn(Arrays.asList(
				new BodyType(1L, "bodyType", "description", 32, 500, "url")
			));
		given(port.findWheelDrivesByTrim(any()))
			.willReturn(Arrays.asList(
				new WheelDrive(1L, "wheelDrive", "description", 32, 500, "url")
			));
		given(port.findUnselectableWheelDriveIdsByEngine(any()))
			.willReturn(Arrays.asList(2L, 3L));
		given(port.findExteriorColorsByTrim(any()))
			.willReturn(Arrays.asList(
				new ExteriorColor(1L, "Exterior Color", 500,
					22, "colorUrl", "coloredUrl")
			));
		given(port.findInteriorColorsByTrim(any()))
			.willReturn(Arrays.asList(
				new InteriorColor(1L, "Interior Color", 500,
					22, "colorUrl", "coloredUrl")
			));

		Option option1 = Option.builder()
			.id(1L)
			.price(500)
			.build();
		Option option2 = Option.builder()
			.id(2L)
			.price(500)
			.build();
		Option option3 = Option.builder()
			.id(5L)
			.price(500)
			.build();
		Option option4 = Option.builder()
			.id(6L)
			.price(500)
			.build();

		given(port.findUnselectableInteriorColorIdsByExteriorColor(any()))
			.willReturn(Arrays.asList(2L, 3L, 4L));
		given(port.findOptionsByTrim(any()))
			.willReturn(Arrays.asList(option1, option2, option3, option4));
		given(port.findUnselectableOptionIdsByEngine(any()))
			.willReturn(Arrays.asList(10L));
		given(port.findUnselectableOptionIdsByWheelDrive(any()))
			.willReturn(Arrays.asList(11L));
		given(port.findUnselectableOptionIdsByBodyType(any()))
			.willReturn(Arrays.asList(12L));
		given(port.findUnselectableOptionIdsByInteriorColor(any()))
			.willReturn(Arrays.asList(13L));

		UUID uuid = UUID.fromString("62dd98f0-bd8e-11ed-93ab-325096b39f47");
		given(port.createEstimate(any(), any(), any(), any(), any(), any(), any(), any(), any()))
			.willReturn(uuid);
		// when
		CreateEstimateResponse response = useCase.execute(request);

		// then
		Assertions.assertThat(response.getEstimateUuid()).isEqualTo(uuid);
	}

	@Test
	@DisplayName("잘못된 옵션이 포함되어 있습니다")
	void invalidOptions() {
		// given
		EstimateOptionRequestDto estimateOptionRequestDto1 = new EstimateOptionRequestDto(1L, 500);
		EstimateOptionRequestDto estimateOptionRequestDto2 = new EstimateOptionRequestDto(2L, 500);
		EstimateOptionRequestDto estimateOptionRequestDto3 = new EstimateOptionRequestDto(5L, 500);
		EstimateOptionRequestDto estimateOptionRequestDto4 = new EstimateOptionRequestDto(6L, 500);

		CreateEstimateRequest request = CreateEstimateRequest.builder()
			.modelId(1L)
			.trimId(1L)
			.trimPrice(500)
			.engineId(1L)
			.enginePrice(500)
			.wheelDriveId(1L)
			.wheelDrivePrice(500)
			.bodyTypeId(1L)
			.bodyTypePrice(500)
			.exteriorColorId(1L)
			.exteriorColorPrice(500)
			.interiorColorId(1L)
			.interiorColorPrice(500)
			.selectOptions(Arrays.asList(estimateOptionRequestDto1, estimateOptionRequestDto2))
			.selectOptionPrice(1000)
			.considerOptions(Arrays.asList(estimateOptionRequestDto3, estimateOptionRequestDto4))
			.totalPrice(4000)
			.build();

		given(port.findModels()).willReturn(Arrays.asList(
			new Model(1L, "model", "url", 1000, Type.SUV)
			));
		given(port.findTrimsByModel(any()))
			.willReturn(Arrays.asList(
				new Trim(1L, "model", "description", 22, 500, "url")
			));
		given(port.findEnginesByTrim(any()))
			.willReturn(Arrays.asList(
				new Engine(1L, "engine", "description", 25, 500, "url",
					22.0, 33.4, 22, 45.5)
			));
		given(port.findBodyTypesByModel(any()))
			.willReturn(Arrays.asList(
				new BodyType(1L, "bodyType", "description", 32, 500, "url")
			));
		given(port.findWheelDrivesByTrim(any()))
			.willReturn(Arrays.asList(
				new WheelDrive(1L, "wheelDrive", "description", 32, 500, "url")
			));
		given(port.findUnselectableWheelDriveIdsByEngine(any()))
			.willReturn(Arrays.asList(2L, 3L));
		given(port.findExteriorColorsByTrim(any()))
			.willReturn(Arrays.asList(
				new ExteriorColor(1L, "Exterior Color", 500,
					22, "colorUrl", "coloredUrl")
			));
		given(port.findInteriorColorsByTrim(any()))
			.willReturn(Arrays.asList(
				new InteriorColor(1L, "Interior Color", 500,
					22, "colorUrl", "coloredUrl")
			));

		Option option1 = Option.builder()
			.id(1L)
			.price(500)
			.build();
		Option option2 = Option.builder()
			.id(2L)
			.price(500)
			.build();
		Option option3 = Option.builder()
			.id(5L)
			.price(500)
			.build();
		Option option4 = Option.builder()
			.id(6L)
			.price(500)
			.build();

		given(port.findUnselectableInteriorColorIdsByExteriorColor(any()))
			.willReturn(Arrays.asList(2L, 3L, 4L));
		given(port.findOptionsByTrim(any()))
			.willReturn(Arrays.asList(option1, option2, option3, option4));
		given(port.findUnselectableOptionIdsByEngine(any()))
			.willReturn(Arrays.asList(1L));
		given(port.findUnselectableOptionIdsByWheelDrive(any()))
			.willReturn(Arrays.asList(11L));
		given(port.findUnselectableOptionIdsByBodyType(any()))
			.willReturn(Arrays.asList(12L));
		given(port.findUnselectableOptionIdsByInteriorColor(any()))
			.willReturn(Arrays.asList(13L));

		// when then
		Assertions.assertThatThrownBy(() -> useCase.execute(request)).isInstanceOf(InvalidEstimationException.class);
	}
}
