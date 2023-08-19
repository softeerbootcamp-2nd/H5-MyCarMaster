package softeer.be_my_car_master.api.model.usecase;

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

import softeer.be_my_car_master.api.model.dto.response.GetModelsResponse;
import softeer.be_my_car_master.api.model.dto.response.ModelDto;
import softeer.be_my_car_master.api.model.usecase.get_models.GetModelsPort;
import softeer.be_my_car_master.api.model.usecase.get_models.GetModelsUseCase;
import softeer.be_my_car_master.domain.model.Model;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetModelsUseCase Test")
class GetModelsUseCaseTest {

	@InjectMocks
	private GetModelsUseCase getModelsUseCase;

	@Mock
	private GetModelsPort getModelsPort;

	@Test
	@DisplayName("모델 목록을 조회합니다")
	void execute() {
		// given
		Model model = Model.builder()
			.id(1L)
			.name("name")
			.imgUrl("image")
			.build();
		given(getModelsPort.findModels()).willReturn(Arrays.asList(model));

		// when
		GetModelsResponse response = getModelsUseCase.execute();

		// then
		List<ModelDto> models = response.getModels();
		ModelDto expected = models.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(models).isNotNull();
			softAssertions.assertThat(models).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(model.getId());
			softAssertions.assertThat(expected.getName()).isEqualTo(model.getName());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(model.getImgUrl());
		});
	}
}
