package softeer.bemycarmaster.api.domain.model.usecase;

import static org.assertj.core.api.Assertions.*;
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

import softeer.bemycarmaster.api.domain.model.domain.Model;
import softeer.bemycarmaster.api.domain.model.dto.response.GetModelsResponse;
import softeer.bemycarmaster.api.domain.model.dto.response.ModelDto;
import softeer.bemycarmaster.api.domain.model.usecase.port.ModelPort;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetModelsUseCase Test")
class GetModelsUseCaseTest {

	@InjectMocks
	private GetModelsUseCase getModelsUseCase;

	@Mock
	private ModelPort modelPort;

	@Test
	@DisplayName("모델 목록을 조회합니다")
	void execute() {
		// given
		Model model = Model.builder()
			.id(1L)
			.name("name")
			.imgUrl("image")
			.build();
		given(modelPort.findModels()).willReturn(Arrays.asList(model));

		// when
		GetModelsResponse getModelsResponse = getModelsUseCase.execute();

		// then
		List<ModelDto> models = getModelsResponse.getModels();
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
