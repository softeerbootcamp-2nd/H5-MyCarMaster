package softeer.be_my_car_master.api.trim.usecase;

import static org.mockito.BDDMockito.*;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.api.trim.dto.response.TrimDefaultOptionDto;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetTrimDefaultOptionsUseCase Test")
public class GetTrimDefaultOptionsUseCaseTest {

	@InjectMocks
	private GetTrimDefaultOptionsUseCase getTrimDefaultOptionsUseCase;

	@Mock
	private OptionPort optionPort;

	@Test
	@DisplayName("트림의 기본 옵션 목록을 조회합니다")
	void execute() {
		// given
		Option option = Option.builder()
			.id(1L)
			.name("임의의 옵션")
			.category(Category.SAFE)
			.description("옵션 상세설명")
			.imgUrl("imgUrl")
			.build();

		given(optionPort.findDefaultOptionsByTrim(any())).willReturn(List.of(option));

		// when
		GetTrimDefaultOptionsResponse getTrimDefaultOptionsResponse = getTrimDefaultOptionsUseCase.execute(1L);

		// then
		List<TrimDefaultOptionDto> trimDefaultOptions = getTrimDefaultOptionsResponse.getDefaultOptions();
		TrimDefaultOptionDto optionExpected = trimDefaultOptions.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(trimDefaultOptions).isNotNull();
			softAssertions.assertThat(trimDefaultOptions).hasSize(1);
			softAssertions.assertThat(optionExpected.getId()).isEqualTo(option.getId());
			softAssertions.assertThat(optionExpected.getName()).isEqualTo(option.getName());
			softAssertions.assertThat(optionExpected.getCategory()).isEqualTo(option.getCategoryValue());
			softAssertions.assertThat(optionExpected.getDescription()).isEqualTo(option.getDescription());
			softAssertions.assertThat(optionExpected.getImgUrl()).isEqualTo(option.getImgUrl());
		});
	}
}
