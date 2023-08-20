package softeer.be_my_car_master.domain.body_type;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BodyType Test")
class BodyTypeTest {

	@Test
	@DisplayName("id와 price로 동일한 바디 타입인지 확인")
	void isRightBodyType() {
		// given
		BodyType bodyType = BodyType.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actual = bodyType.isRightBodyType(1L, 20000);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id나 price가 다르면 동일한 body type 아님")
	void isNotRightBodyType() {
		// given
		BodyType bodyType = BodyType.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actualDiffId = bodyType.isRightBodyType(2L, 20000);
		boolean actualDiffPrice = bodyType.isRightBodyType(1L, 10000);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actualDiffId).isFalse();
			softAssertions.assertThat(actualDiffPrice).isFalse();
		});
	}
}
