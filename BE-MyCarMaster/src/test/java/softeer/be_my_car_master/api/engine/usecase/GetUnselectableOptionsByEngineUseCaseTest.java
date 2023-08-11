package softeer.be_my_car_master.api.engine.usecase;

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

import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.api.engine.dto.response.UnselectableOptionDto;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.exception.InvalidOptionIdException;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetUnselectableOptionsByEngineUseCase Test")
class GetUnselectableOptionsByEngineUseCaseTest {

	@InjectMocks
	private GetUnselectableOptionsByEngineUseCase getUnselectableOptionsByEngineUseCase;

	@Mock
	private OptionPort optionPort;

	@Test
	@DisplayName("엔진에 따라 선택 불가능한 옵션 목록을 조회합니다")
	void execute() {
		// given
		Option option1 = Option.builder()
			.id(1L)
			.name("임의의 옵션")
			.category("SAFE")
			.summary("옵션 요약")
			.description("옵션 상세설명")
			.imgUrl("imgUrl")
			.price(100000)
			.ratio(22)
			.isSuper(false)
			.subOptions(null)
			.tag(null)
			.build();

		Option option2 = Option.builder()
			.id(2L)
			.name("임의의 옵션2")
			.category("SAFE")
			.summary("옵션 요약")
			.description("옵션 상세설명")
			.imgUrl("imgUrl")
			.price(100000)
			.ratio(22)
			.isSuper(false)
			.subOptions(null)
			.tag(null)
			.build();

		given(optionPort.findSelectableOptionsByTrimId(any())).willReturn(Arrays.asList(option1, option2));
		given(optionPort.findUnselectableOptionIdsByEngineId(any())).willReturn(Arrays.asList(1L, 3L));

		// when
		GetUnselectableOptionsByEngineResponse getUnselectableOptionsByEngineResponse =
			getUnselectableOptionsByEngineUseCase.execute(1L, 1L, Arrays.asList(1L, 2L));

		// then
		List<UnselectableOptionDto> options = getUnselectableOptionsByEngineResponse.getUnselectableOptions();
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
	@DisplayName("OptionIds가 트림에서 선택 불가능한 옵션을 포함한다면 InvalidOptionIdException이 발생합니다")
	void invalidOptionIds() {
		// given
		Option option1 = Option.builder()
			.id(1L)
			.name("임의의 옵션")
			.category("SAFE")
			.summary("옵션 요약")
			.description("옵션 상세설명")
			.imgUrl("imgUrl")
			.price(100000)
			.ratio(22)
			.isSuper(false)
			.subOptions(null)
			.tag(null)
			.build();

		given(optionPort.findSelectableOptionsByTrimId(any())).willReturn(Arrays.asList(option1));
		given(optionPort.findUnselectableOptionIdsByEngineId(any())).willReturn(Arrays.asList(1L, 3L));

		// when
		// then
		assertThrows(
			InvalidOptionIdException.class,
			() -> getUnselectableOptionsByEngineUseCase.execute(1L, 1L, Arrays.asList(1L, 2L))
		);
	}
}
