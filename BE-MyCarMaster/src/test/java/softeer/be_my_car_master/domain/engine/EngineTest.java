package softeer.be_my_car_master.domain.engine;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Engine Test")
class EngineTest {

	@Test
	@DisplayName("id와 price로 동일한 엔진인지 확인")
	void isRightEngine() {
		// given
		Engine engine = Engine.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actual = engine.isRightEngine(1L, 20000);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id나 price가 다르면 동일한 엔진 아님")
	void isNotRightEngine() {
		// given
		Engine engine = Engine.builder()
			.id(1L)
			.price(20000)
			.build();


		// when
		boolean actualDiffId = engine.isRightEngine(2L, 20000);
		boolean actualDiffPrice = engine.isRightEngine(1L, 10000);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actualDiffId).isFalse();
			softAssertions.assertThat(actualDiffPrice).isFalse();
		});
	}
}
