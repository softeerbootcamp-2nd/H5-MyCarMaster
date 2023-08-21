package softeer.be_my_car_master.application.engine.usecase;

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

import softeer.be_my_car_master.application.engine.dto.response.EngineDto;
import softeer.be_my_car_master.application.engine.dto.response.GetEnginesResponse;
import softeer.be_my_car_master.application.engine.usecase.get_engines.GetEnginesPort;
import softeer.be_my_car_master.application.engine.usecase.get_engines.GetEnginesUseCase;
import softeer.be_my_car_master.domain.engine.Engine;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetEnginesUseCase Test")
class GetEnginesUseCaseTest {

	@InjectMocks
	private GetEnginesUseCase getEnginesUseCase;

	@Mock
	private GetEnginesPort getEnginesPort;

	@Test
	@DisplayName("엔진 목록을 조회합니다")
	void execute() {
		// given
		Engine engine = Engine.builder()
			.id(1L)
			.name("가솔린 3.8")
			.description("가솔린 3.8 Description")
			.price(0)
			.ratio(22)
			.imgUrl("imgUrl")
			.power(295)
			.toque(36.2)
			.fuelMin(8.0)
			.fuelMax(9.2)
			.build();
		given(getEnginesPort.findEnginesByTrim(any())).willReturn(Arrays.asList(engine));

		// when
		GetEnginesResponse response = getEnginesUseCase.execute(1L);

		// then
		List<EngineDto> engines = response.getEngines();
		EngineDto expected = engines.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(engines).isNotNull();
			softAssertions.assertThat(engines).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(engine.getId());
			softAssertions.assertThat(expected.getName()).isEqualTo(engine.getName());
			softAssertions.assertThat(expected.getDescription()).isEqualTo(engine.getDescription());
			softAssertions.assertThat(expected.getPrice()).isEqualTo(engine.getPrice());
			softAssertions.assertThat(expected.getRatio()).isEqualTo(engine.getRatio());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(engine.getImgUrl());
			softAssertions.assertThat(expected.getPower()).isEqualTo(engine.getPower());
			softAssertions.assertThat(expected.getToque()).isEqualTo(engine.getToque());
			softAssertions.assertThat(expected.getFuelMin()).isEqualTo(engine.getFuelMin());
			softAssertions.assertThat(expected.getFuelMax()).isEqualTo(engine.getFuelMax());
		});
	}
}
