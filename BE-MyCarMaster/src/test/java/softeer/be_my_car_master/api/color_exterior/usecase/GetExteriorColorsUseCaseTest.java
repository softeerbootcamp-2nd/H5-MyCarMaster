package softeer.be_my_car_master.api.color_exterior.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import softeer.be_my_car_master.api.color_exterior.dto.response.ExteriorColorDto;
import softeer.be_my_car_master.api.color_exterior.dto.response.GetExteriorColorsResponse;
import softeer.be_my_car_master.api.color_exterior.usecase.port.ExteriorColorPort;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetExteriorColorsUseCase Test")
class GetExteriorColorsUseCaseTest {

	@InjectMocks
	private GetExteriorColorsUseCase getExteriorColorsUseCase;

	@Mock
	private ExteriorColorPort exteriorColorPort;

	@Test
	@DisplayName("모델 목록을 조회합니다")
	void execute() {
		// given
		ExteriorColor exteriorColor = ExteriorColor.builder()
			.id(1L)
			.name("blue")
			.price(10000)
			.ratio(22)
			.colorImgUrl("colorImgUrl")
			.coloredImgUrl("coloredImgUrl")
			.build();
		given(exteriorColorPort.findSelectableExteriorColorsByTrimId(any())).willReturn(Arrays.asList(exteriorColor));

		// when
		GetExteriorColorsResponse getExteriorColorsResponse = getExteriorColorsUseCase.execute(1L);

		// then
		List<ExteriorColorDto> exteriorColors = getExteriorColorsResponse.getColors();
		ExteriorColorDto expected = exteriorColors.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(exteriorColors).isNotNull();
			softAssertions.assertThat(exteriorColors).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(exteriorColor.getId());
			softAssertions.assertThat(expected.getPrice()).isEqualTo(exteriorColor.getPrice());
			softAssertions.assertThat(expected.getRatio()).isEqualTo(exteriorColor.getRatio());
			softAssertions.assertThat(expected.getColorImgUrl()).isEqualTo(exteriorColor.getColorImgUrl());
			softAssertions.assertThat(expected.getColoredImgUrl()).isEqualTo(exteriorColor.getColoredImgUrl());
		});
	}
}
