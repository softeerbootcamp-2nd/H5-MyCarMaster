package softeer.be_my_car_master.domain.wheel_dirve;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WheelDrive Test")
class WheelDriveTest {

	@Test
	@DisplayName("선택 불가능한 구동 방식인지 확인")
	void isSelectable() {
		// given
		WheelDrive wheelDrive = WheelDrive.builder()
			.id(1L)
			.build();

		List<Long> unselectableIds = Arrays.asList(1L, 2L, 3L, 4L);

		// when
		boolean actual = wheelDrive.isSelectable(unselectableIds);

		//then
		Assertions.assertThat(actual).isFalse();
	}

	@Test
	@DisplayName("선택 가능한 구동 방식인지 확인")
	void isNotSelectable() {
		// given
		WheelDrive wheelDrive = WheelDrive.builder()
			.id(5L)
			.build();

		List<Long> unselectableIds = Arrays.asList(1L, 2L, 3L, 4L);

		// when
		boolean actual = wheelDrive.isSelectable(unselectableIds);

		//then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id와 price로 동일한 구동 방식인지 확인")
	void isRightWheelDrive() {
		// given
		WheelDrive wheelDrive = WheelDrive.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actual = wheelDrive.isRightWheelDrive(1L, 20000);

		// then
		Assertions.assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("id나 price가 다르면 동일한 구동 방식 아님")
	void isNotRightWheelDrive() {
		// given
		WheelDrive wheelDrive = WheelDrive.builder()
			.id(1L)
			.price(20000)
			.build();

		// when
		boolean actualDiffId = wheelDrive.isRightWheelDrive(2L, 20000);
		boolean actualDiffPrice = wheelDrive.isRightWheelDrive(1L, 10000);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actualDiffId).isFalse();
			softAssertions.assertThat(actualDiffPrice).isFalse();
		});
	}
}
