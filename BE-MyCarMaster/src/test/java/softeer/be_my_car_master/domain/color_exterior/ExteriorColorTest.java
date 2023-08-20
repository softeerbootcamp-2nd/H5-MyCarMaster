package softeer.be_my_car_master.domain.color_exterior;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ExteriorColor Test")
class ExteriorColorTest {

	@Test
	@DisplayName("id와 price로 동일한 내장 색상인지 확인")
	void isRightExteriorColor() {
		// given
		ExteriorColor exteriorColor = ExteriorColor.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actual = exteriorColor.isRightExteriorColor(1L, 20000);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id나 price가 다르면 동일한 내장 색상 아님")
	void isNotRightExteriorColor() {
		// given
		ExteriorColor exteriorColor = ExteriorColor.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actualDiffId = exteriorColor.isRightExteriorColor(2L, 20000);
		boolean actualDiffPrice = exteriorColor.isRightExteriorColor(1L, 10000);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actualDiffId).isFalse();
			softAssertions.assertThat(actualDiffPrice).isFalse();
		});
	}
}
