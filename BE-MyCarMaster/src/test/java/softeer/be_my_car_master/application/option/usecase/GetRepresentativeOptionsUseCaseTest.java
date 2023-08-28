package softeer.be_my_car_master.application.option.usecase;

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

import softeer.be_my_car_master.application.option.dto.response.AppliedOptionDto;
import softeer.be_my_car_master.application.option.dto.response.FilterDto;
import softeer.be_my_car_master.application.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.application.option.dto.response.RepresentativeOptionDto;
import softeer.be_my_car_master.application.option.usecase.get_representative_options.GetRepresentativeOptionsPort;
import softeer.be_my_car_master.application.option.usecase.get_representative_options.GetRepresentativeOptionsUseCase;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetRepresentativeOptionsUseCase Test")
public class GetRepresentativeOptionsUseCaseTest {

	@InjectMocks
	private GetRepresentativeOptionsUseCase getRepresentativeOptionsUseCase;

	@Mock
	private GetRepresentativeOptionsPort port;

	@Test
	@DisplayName("모델의 대표 옵션 목록을 조회합니다")
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

		given(port.findRepresentativeOptionsByModel(any())).willReturn(Arrays.asList(option));
		given(port.findAppliedOptionsByModelAndOptions(any(), anyList())).willReturn(Arrays.asList(option));
		given(port.findAdditionalTrimIdsByOption(any())).willReturn(Arrays.asList(2L, 3L));
		given(port.findDefaultTrimIdsByOption(any())).willReturn(Arrays.asList(4L));

		given(port.findTrimIdsByModel(any())).willReturn(Arrays.asList(1L, 2L, 3L, 4L));

		// when
		GetRepresentativeOptionsResponse response = getRepresentativeOptionsUseCase.execute(1L);

		// then
		List<RepresentativeOptionDto> representativeOptions =
			response.getRepresentativeOptions();
		RepresentativeOptionDto representativeOptionExpected = representativeOptions.get(0);
		FilterDto filterExpected = representativeOptionExpected.getFilter();
		AppliedOptionDto appliedOptionExpected = representativeOptionExpected.getAppliedOption();

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(representativeOptions).isNotNull();
			softAssertions.assertThat(representativeOptions).hasSize(1);
			softAssertions.assertThat(representativeOptionExpected.getId()).isEqualTo(option.getId());
			softAssertions.assertThat(representativeOptionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(representativeOptionExpected.getSummary()).isEqualTo(option.getSummary());
			softAssertions.assertThat(representativeOptionExpected.getDescription()).isEqualTo(option.getDescription());
			softAssertions.assertThat(representativeOptionExpected.getImgUrl()).isEqualTo(option.getImgUrl());
			softAssertions.assertThat(filterExpected.getUnavailableTrimIds()).containsAll(Arrays.asList(1L));
			softAssertions.assertThat(filterExpected.getAdditionalTrimIds()).containsAll(Arrays.asList(2L, 3L));
			softAssertions.assertThat(filterExpected.getDefaultTrimIds()).containsAll(Arrays.asList(4L));
			softAssertions.assertThat(appliedOptionExpected.getId()).isEqualTo(option.getId());
			softAssertions.assertThat(appliedOptionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(appliedOptionExpected.getPrice()).isEqualTo(option.getPrice());
			softAssertions.assertThat(appliedOptionExpected.getCategory()).isEqualTo(option.getCategoryValue());
			softAssertions.assertThat(appliedOptionExpected.getImgUrl()).isEqualTo(option.getImgUrl());
		});
	}
}
