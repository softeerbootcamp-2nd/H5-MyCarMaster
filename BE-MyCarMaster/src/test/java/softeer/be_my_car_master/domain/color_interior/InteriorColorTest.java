package softeer.be_my_car_master.domain.color_interior;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("InteriorColor Test")
class InteriorColorTest {

	@Test
	@DisplayName("선택 불가능한 내장 색상인지 확인")
	void isSelectable() {
		// given
		InteriorColor interiorColor = InteriorColor.builder()
			.id(1L)
			.build();

		List<Long> unselectableIds = Arrays.asList(1L, 2L, 3L, 4L);

		// when
		boolean actual = interiorColor.isSelectable(unselectableIds);

		//then
		Assertions.assertThat(actual).isFalse();
	}

	@Test
	@DisplayName("선택 가능한 내장 색상인지 확인")
	void isNotSelectable() {
		// given
		InteriorColor interiorColor = InteriorColor.builder()
			.id(5L)
			.build();

		List<Long> unselectableIds = Arrays.asList(1L, 2L, 3L, 4L);

		// when
		boolean actual = interiorColor.isSelectable(unselectableIds);

		//then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id와 price로 동일한 내장 색상인지 확인")
	void isRightInteriorColor() {
		// given
		InteriorColor interiorColor = InteriorColor.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actual = interiorColor.isRightInteriorColor(1L, 20000);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id나 price가 다르면 동일한 내장 색상 아님")
	void isNotRightInteriorColor() {
		// given
		InteriorColor interiorColor = InteriorColor.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actualDiffId = interiorColor.isRightInteriorColor(2L, 20000);
		boolean actualDiffPrice = interiorColor.isRightInteriorColor(1L, 10000);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actualDiffId).isFalse();
			softAssertions.assertThat(actualDiffPrice).isFalse();
		});
	}
}
