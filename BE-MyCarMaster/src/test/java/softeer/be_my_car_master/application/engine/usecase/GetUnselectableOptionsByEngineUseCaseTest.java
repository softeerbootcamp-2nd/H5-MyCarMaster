package softeer.be_my_car_master.application.engine.usecase;

import static org.junit.jupiter.api.Assertions.*;
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

import softeer.be_my_car_master.application.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.application.engine.dto.response.UnselectableOptionDto;
import softeer.be_my_car_master.application.engine.exception.InvalidOptionByTrimException;
import softeer.be_my_car_master.application.option.usecase.get_unselectable_options_by_engine.GetUnselectableOptionsByEnginePort;
import softeer.be_my_car_master.application.option.usecase.get_unselectable_options_by_engine.GetUnselectableOptionsByEngineUseCase;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetUnselectableOptionsByEngineUseCase Test")
class GetUnselectableOptionsByEngineUseCaseTest {

	@InjectMocks
	private GetUnselectableOptionsByEngineUseCase useCase;

	@Mock
	private GetUnselectableOptionsByEnginePort port;

	@Test
	@DisplayName("엔진에 따라 선택 불가능한 옵션 목록을 조회합니다")
	void execute() {
		// given
		Option option1 = Option.builder()
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

		given(port.findOptionIdsByTrim(any())).willReturn(Arrays.asList(1L, 2L, 3L));
		given(port.findUnselectableOptionIdsByEngine(any())).willReturn(Arrays.asList(1L, 3L));
		given(port.findUnselectableOptions(any(), any())).willReturn(Arrays.asList(option1));

		// when
		GetUnselectableOptionsByEngineResponse response
			= useCase.execute(1L, 1L, Arrays.asList(1L, 2L));

		// then
		List<UnselectableOptionDto> options = response.getUnselectableOptions();
		UnselectableOptionDto optionExpected = options.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(options).isNotNull();
			softAssertions.assertThat(options).hasSize(1);
			softAssertions.assertThat(optionExpected.getId()).isEqualTo(option1.getId());
			softAssertions.assertThat(optionExpected.getName()).isEqualTo(option1.getName());
			softAssertions.assertThat(optionExpected.getPrice()).isEqualTo(option1.getPrice());
		});
	}

	@Test
	@DisplayName("OptionIds가 트림에서 선택 불가능한 옵션을 포함한다면 InvalidOptionException이 발생합니다")
	void invalidOptionIds() {
		// given
		given(port.findOptionIdsByTrim(any())).willReturn(Arrays.asList(2L, 3L));

		// when
		// then
		assertThrows(
			InvalidOptionByTrimException.class,
			() -> useCase.execute(1L, 1L, Arrays.asList(1L, 2L))
		);
	}
}
