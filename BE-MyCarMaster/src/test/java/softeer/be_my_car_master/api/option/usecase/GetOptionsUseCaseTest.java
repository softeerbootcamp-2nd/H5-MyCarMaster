package softeer.be_my_car_master.api.option.usecase;

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

import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.OptionDto;
import softeer.be_my_car_master.api.option.usecase.get_options.GetOptionsPort;
import softeer.be_my_car_master.api.option.usecase.get_options.GetOptionsUseCase;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetOptionsUseCase Test")
class GetOptionsUseCaseTest {

	@InjectMocks
	private GetOptionsUseCase getOptionsUseCase;

	@Mock
	private GetOptionsPort getOptionsPort;

	@Test
	@DisplayName("선택 가능한 옵션 목록을 조회합니다")
	void execute() {
		// given
		Option option = Option.builder()
			.id(1L)
			.name("임의의 옵션")
			.category(Category.SAFE)
			.summary("옵션 요약")
			.description("옵션 상세설명")
			.imgUrl("imgUrl")
			.price(100000)
			.ratio(22)
			.isSuper(false)
			.subOptions(null)
			.tag(null)
			.build();

		given(getOptionsPort.findOptionsByTrim(any())).willReturn(Arrays.asList(option));
		given(getOptionsPort.findUnselectableOptionIdsByEngine(any())).willReturn(Arrays.asList(2L, 3L));
		given(getOptionsPort.findUnselectableOptionIdsByWheelDrive(any())).willReturn(Arrays.asList(2L, 3L));
		given(getOptionsPort.findUnselectableOptionIdsByBodyType(any())).willReturn(Arrays.asList(2L, 3L));
		given(getOptionsPort.findUnselectableOptionIdsByInteriorColor(any())).willReturn(Arrays.asList(2L, 3L));

		given(getOptionsPort.findSingleSelectableTags()).willReturn(Arrays.asList("N Performance"));

		// when
		GetOptionsResponse getOptionsResponse = getOptionsUseCase.execute(1L, 1L, 1L, 1L, 1L);

		// then
		List<OptionDto> options = getOptionsResponse.getOptions();
		OptionDto optionExpected = options.get(0);

		List<String> exclusiveTags = getOptionsResponse.getExclusiveTags();
		String exclusiveTagExpected = exclusiveTags.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(options).isNotNull();
			softAssertions.assertThat(options).hasSize(1);
			softAssertions.assertThat(optionExpected.getId()).isEqualTo(option.getId());
			softAssertions.assertThat(optionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(optionExpected.getCategory()).isEqualTo(option.getCategoryValue());
			softAssertions.assertThat(optionExpected.getSummary()).isEqualTo(option.getSummary());
			softAssertions.assertThat(optionExpected.getDescription()).isEqualTo(option.getDescription());
			softAssertions.assertThat(optionExpected.getImgUrl()).isEqualTo(option.getImgUrl());
			softAssertions.assertThat(optionExpected.getPrice()).isEqualTo(option.getPrice());
			softAssertions.assertThat(optionExpected.getRatio()).isEqualTo(option.getRatio());
			softAssertions.assertThat(optionExpected.getTag()).isEqualTo(option.getTag());

			softAssertions.assertThat(exclusiveTags).hasSize(1);
			softAssertions.assertThat(exclusiveTagExpected).isEqualTo("N Performance");
		});
	}
}
