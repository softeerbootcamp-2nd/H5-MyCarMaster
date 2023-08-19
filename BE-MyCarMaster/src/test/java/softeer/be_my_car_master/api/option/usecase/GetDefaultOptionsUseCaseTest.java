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

import softeer.be_my_car_master.api.option.dto.response.DefaultOptionDto;
import softeer.be_my_car_master.api.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetDefaultOptionsUseCase Test")
class GetDefaultOptionsUseCaseTest {

	@InjectMocks
	private GetDefaultOptionsUseCase getDefaultOptionsUseCase;

	@Mock
	private OptionPort optionPort;

	@Test
	@DisplayName("기본 옵션 목록을 조회합니다")
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

		given(optionPort.findDefaultOptionsByTrim(any())).willReturn(Arrays.asList(option));
		given(optionPort.findUnselectableOptionIdsByEngineId(any())).willReturn(Arrays.asList(2L, 3L));
		given(optionPort.findUnselectableOptionIdsByWheelDriveId(any())).willReturn(Arrays.asList(2L, 3L));
		given(optionPort.findUnselectableOptionIdsByBodyTypeId(any())).willReturn(Arrays.asList(2L, 3L));

		// when
		GetDefaultOptionsResponse getDefaultOptionsResponse = getDefaultOptionsUseCase.execute(1L, 1L, 1L, 1L);

		// then
		List<DefaultOptionDto> options = getDefaultOptionsResponse.getDefaultOptions();
		DefaultOptionDto optionExpected = options.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(options).isNotNull();
			softAssertions.assertThat(options).hasSize(1);
			softAssertions.assertThat(optionExpected.getId()).isEqualTo(option.getId());
			softAssertions.assertThat(optionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(optionExpected.getCategory()).isEqualTo(option.getCategoryValue());
			softAssertions.assertThat(optionExpected.getDescription()).isEqualTo(option.getDescription());
			softAssertions.assertThat(optionExpected.getImgUrl()).isEqualTo(option.getImgUrl());
		});
	}
}
