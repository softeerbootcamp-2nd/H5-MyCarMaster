package softeer.be_my_car_master.application.trim.usecase;

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

import softeer.be_my_car_master.application.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.application.trim.dto.response.TrimDto;
import softeer.be_my_car_master.application.trim.usecase.get_trims.GetTrimsPort;
import softeer.be_my_car_master.application.trim.usecase.get_trims.GetTrimsUseCase;
import softeer.be_my_car_master.domain.trim.Trim;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetTrimsUseCase Test")
class GetTrimsUseCaseTest {

	@InjectMocks
	private GetTrimsUseCase getTrimsUseCase;

	@Mock
	private GetTrimsPort getTrimsPort;

	@Test
	@DisplayName("모델 목록을 조회합니다")
	void execute() {
		// given
		Trim trim = Trim.builder()
			.id(1L)
			.name("name")
			.description("description")
			.ratio(22)
			.price(10000)
			.imgUrl("image")
			.build();
		given(getTrimsPort.findTrimsByModel(any())).willReturn(Arrays.asList(trim));

		// when
		GetTrimsResponse getTrimsResponse = getTrimsUseCase.execute(1L);

		// then
		List<TrimDto> trims = getTrimsResponse.getTrims();
		TrimDto expected = trims.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(trims).isNotNull();
			softAssertions.assertThat(trims).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(trim.getId());
			softAssertions.assertThat(expected.getName()).isEqualTo(trim.getName());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(trim.getImgUrl());
		});
	}
}
