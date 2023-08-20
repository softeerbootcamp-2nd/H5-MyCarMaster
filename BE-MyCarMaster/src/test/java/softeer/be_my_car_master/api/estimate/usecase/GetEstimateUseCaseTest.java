package softeer.be_my_car_master.api.estimate.usecase;

import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import softeer.be_my_car_master.api.estimate.dto.response.EstimateBodyTypeDto;
import softeer.be_my_car_master.api.estimate.dto.response.EstimateEngineDto;
import softeer.be_my_car_master.api.estimate.dto.response.EstimateExteriorColorDto;
import softeer.be_my_car_master.api.estimate.dto.response.EstimateInteriorColorDto;
import softeer.be_my_car_master.api.estimate.dto.response.EstimateOptionDto;
import softeer.be_my_car_master.api.estimate.dto.response.EstimateTrimDto;
import softeer.be_my_car_master.api.estimate.dto.response.EstimateWheelDriveDto;
import softeer.be_my_car_master.api.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.api.estimate.usecase.get_estimate.GetEstimatePort;
import softeer.be_my_car_master.api.estimate.usecase.get_estimate.GetEstimateUseCase;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetEstimateUseCase Test")
public class GetEstimateUseCaseTest {

	@InjectMocks
	private GetEstimateUseCase useCase;

	@Mock
	private GetEstimatePort port;

	@Test
	@DisplayName("견적서를 조회합니다")
	void execute() {
		// given
		Trim trim = Trim.builder()
			.name("트림")
			.price(1000)
			.build();
		Engine engine = Engine.builder()
			.name("엔진")
			.price(10000)
			.build();
		WheelDrive wheelDrive = WheelDrive.builder()
			.name("구동방식")
			.price(10000)
			.build();
		BodyType bodyType = BodyType.builder()
			.name("바디타입")
			.price(10000)
			.build();
		ExteriorColor exteriorColor = ExteriorColor.builder()
			.name("외장색상")
			.price(10000)
			.colorImgUrl("img url")
			.coloredImgUrl("img url")
			.build();
		InteriorColor interiorColor = InteriorColor.builder()
			.name("내장색상")
			.price(10000)
			.colorImgUrl("img url")
			.build();
		Option option = Option.builder()
			.name("옵션")
			.price(10000)
			.imgUrl("img url")
			.build();
		Estimate estimate = Estimate.builder()
			.id(1L)
			.uuid(UUID.randomUUID())
			.trim(trim)
			.engine(engine)
			.wheelDrive(wheelDrive)
			.bodyType(bodyType)
			.exteriorColor(exteriorColor)
			.interiorColor(interiorColor)
			.selectOptions(List.of(option))
			.considerOptions(List.of(option))
			.build();

		given(port.findFullEstimateByUuid(any())).willReturn(Optional.ofNullable(estimate));

		// when
		GetEstimateResponse response = useCase.execute(UUID.randomUUID());

		// then
		EstimateTrimDto trimExpected = response.getTrim();
		EstimateEngineDto engineExpected = response.getEngine();
		EstimateWheelDriveDto wheelDriveExpected = response.getWheelDrive();
		EstimateBodyTypeDto bodyTypeExpected = response.getBodyType();
		EstimateExteriorColorDto exteriorColorExpected = response.getExteriorColor();
		EstimateInteriorColorDto interiorColorExpected = response.getInteriorColor();
		List<EstimateOptionDto> selectedOptionDtos = response.getSelectOptions();
		EstimateOptionDto additionalOptionExpected = selectedOptionDtos.get(0);
		List<EstimateOptionDto> considerOptionDtos = response.getConsiderOptions();
		EstimateOptionDto considerOptionExpected = considerOptionDtos.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(selectedOptionDtos).isNotNull();
			softAssertions.assertThat(selectedOptionDtos).hasSize(1);
			softAssertions.assertThat(considerOptionDtos).isNotNull();
			softAssertions.assertThat(considerOptionDtos).hasSize(1);

			softAssertions.assertThat(trimExpected.getName()).isEqualTo(trim.getName());
			softAssertions.assertThat(trimExpected.getPrice()).isEqualTo(trim.getPrice());
			softAssertions.assertThat(engineExpected.getName()).isEqualTo(engine.getName());
			softAssertions.assertThat(engineExpected.getPrice()).isEqualTo(engine.getPrice());
			softAssertions.assertThat(wheelDriveExpected.getName()).isEqualTo(wheelDrive.getName());
			softAssertions.assertThat(wheelDriveExpected.getPrice()).isEqualTo(wheelDrive.getPrice());
			softAssertions.assertThat(bodyTypeExpected.getName()).isEqualTo(bodyType.getName());
			softAssertions.assertThat(bodyTypeExpected.getPrice()).isEqualTo(bodyType.getPrice());
			softAssertions.assertThat(exteriorColorExpected.getName()).isEqualTo(exteriorColor.getName());
			softAssertions.assertThat(exteriorColorExpected.getPrice()).isEqualTo(exteriorColor.getPrice());
			softAssertions.assertThat(interiorColorExpected.getName()).isEqualTo(interiorColor.getName());
			softAssertions.assertThat(interiorColorExpected.getPrice()).isEqualTo(interiorColor.getPrice());
			softAssertions.assertThat(additionalOptionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(additionalOptionExpected.getPrice()).isEqualTo(option.getPrice());
			softAssertions.assertThat(considerOptionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(considerOptionExpected.getPrice()).isEqualTo(option.getPrice());
		});

	}
}
