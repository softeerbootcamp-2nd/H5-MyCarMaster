package softeer.be_my_car_master.api.color_interior.usecase;

import static org.mockito.ArgumentMatchers.*;
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

import softeer.be_my_car_master.api.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.api.color_interior.dto.response.InteriorColorDto;
import softeer.be_my_car_master.api.color_interior.usecase.port.InteriorColorPort;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetInteriorColorsUseCase Test")
class GetInteriorColorsUseCaseTest {

	@InjectMocks
	private GetInteriorColorsUseCase getInteriorColorsUseCase;

	@Mock
	private InteriorColorPort interiorColorPort;

	@Test
	@DisplayName("내장 색상 목록을 조회합니다")
	void execute() {
		// given
		InteriorColor interiorColor = InteriorColor.builder()
			.id(1L)
			.name("blue")
			.price(10000)
			.ratio(22)
			.colorImgUrl("colorImgUrl")
			.coloredImgUrl("coloredImgUrl")
			.build();
		given(interiorColorPort.findSelectableInteriorColorsByTrimId(any())).willReturn(Arrays.asList(interiorColor));

		given(interiorColorPort.findUnselectableInteriorColorIdsByExteriorColorId(any())).willReturn(
			Arrays.asList(2L, 3L));

		// when
		GetInteriorColorsResponse getInteriorColorsResponse = getInteriorColorsUseCase.execute(1L, 1L);

		// then
		List<InteriorColorDto> interiorColors = getInteriorColorsResponse.getColors();
		InteriorColorDto expected = interiorColors.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(interiorColors).isNotNull();
			softAssertions.assertThat(interiorColors).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(interiorColor.getId());
			softAssertions.assertThat(expected.getPrice()).isEqualTo(interiorColor.getPrice());
			softAssertions.assertThat(expected.getRatio()).isEqualTo(interiorColor.getRatio());
			softAssertions.assertThat(expected.getColorImgUrl()).isEqualTo(interiorColor.getColorImgUrl());
			softAssertions.assertThat(expected.getColoredImgUrl()).isEqualTo(interiorColor.getColoredImgUrl());
		});
	}

	@Test
	@DisplayName("선택 가능한 내장 색상 목록이 존재하지 않습니다")
	void noExistSelectableInteriorColors() {
		// given
		InteriorColor interiorColor = InteriorColor.builder()
			.id(1L)
			.name("blue")
			.price(10000)
			.ratio(22)
			.colorImgUrl("colorImgUrl")
			.coloredImgUrl("coloredImgUrl")
			.build();
		given(interiorColorPort.findSelectableInteriorColorsByTrimId(any())).willReturn(Arrays.asList(interiorColor));

		given(interiorColorPort.findUnselectableInteriorColorIdsByExteriorColorId(any())).willReturn(
			Arrays.asList(1L, 3L));

		// when
		GetInteriorColorsResponse getInteriorColorsResponse = getInteriorColorsUseCase.execute(1L, 1L);

		// then
		List<InteriorColorDto> interiorColors = getInteriorColorsResponse.getColors();

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(interiorColors).isNotNull();
			softAssertions.assertThat(interiorColors).hasSize(0);
		});
	}
}
