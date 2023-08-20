package softeer.be_my_car_master.domain.trim;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;

@DisplayName("Trim Test")
class TrimTest {

	@Test
	@DisplayName("id와 price로 동일한 트림인지 확인")
	void isRightTrim() {
		// given
		Trim trim = Trim.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actual = trim.isRightTrim(1L, 20000);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id나 price가 다르면 동일한 트림 아님")
	void isNotRightTrim() {
		// given
		Trim trim = Trim.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actualDiffId = trim.isRightTrim(2L, 20000);
		boolean actualDiffPrice = trim.isRightTrim(1L, 10000);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actualDiffId).isFalse();
			softAssertions.assertThat(actualDiffPrice).isFalse();
		});
	}
}
