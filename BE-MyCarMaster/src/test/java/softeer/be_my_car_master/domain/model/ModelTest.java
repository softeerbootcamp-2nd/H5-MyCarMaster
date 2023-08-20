package softeer.be_my_car_master.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Model Test")
class ModelTest {

	@Test
	@DisplayName("id로 동일한 모델인지 확인")
	void isRightModel() {
		// given
		Model model = Model.builder()
			.id(1L)
			.build();

		// when
		boolean actual = model.isRightModel(1L);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id 다르면 동일한 모델이 아님")
	void isNotRightModel() {
		// given
		Model model = Model.builder()
			.id(1L)
			.build();

		// when
		boolean actual = model.isRightModel(2L);

		// then
		Assertions.assertThat(actual).isFalse();
	}
}
