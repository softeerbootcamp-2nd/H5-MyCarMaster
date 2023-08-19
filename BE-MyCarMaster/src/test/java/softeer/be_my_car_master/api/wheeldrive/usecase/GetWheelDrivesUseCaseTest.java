package softeer.be_my_car_master.api.wheeldrive.usecase;

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

import softeer.be_my_car_master.api.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.be_my_car_master.api.wheeldrive.dto.response.WheelDriveDto;
import softeer.be_my_car_master.api.wheeldrive.usecase.get_wheel_drives.GetWheelDrivesPort;
import softeer.be_my_car_master.api.wheeldrive.usecase.get_wheel_drives.GetWheelDrivesUseCase;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetWheelDrivesUseCase Test")
class GetWheelDrivesUseCaseTest {

	@InjectMocks
	private GetWheelDrivesUseCase getWheelDrivesUseCase;

	@Mock
	private GetWheelDrivesPort getWheelDrivePort;

	@Test
	@DisplayName("구동방식 목록을 조회합니다")
	void execute() {
		// given
		WheelDrive wheelDrive = WheelDrive.builder()
			.id(1L)
			.name("2WD")
			.description("2WD Description")
			.price(0)
			.ratio(22)
			.imgUrl("imgUrl")
			.build();
		given(getWheelDrivePort.findSelectableWheelDrivesByTrimId(any())).willReturn(Arrays.asList(wheelDrive));

		given(getWheelDrivePort.findUnselectableWheelDriveIdsByEngineId(any())).willReturn(
			Arrays.asList(2L, 3L));

		// when
		GetWheelDrivesResponse getWheelDrivesResponse = getWheelDrivesUseCase.execute(1L, 1L);

		// then
		List<WheelDriveDto> wheelDrives = getWheelDrivesResponse.getWheelDrives();
		WheelDriveDto expected = wheelDrives.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(wheelDrives).isNotNull();
			softAssertions.assertThat(wheelDrives).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(wheelDrive.getId());
			softAssertions.assertThat(expected.getName()).isEqualTo(wheelDrive.getName());
			softAssertions.assertThat(expected.getDescription()).isEqualTo(wheelDrive.getDescription());
			softAssertions.assertThat(expected.getPrice()).isEqualTo(wheelDrive.getPrice());
			softAssertions.assertThat(expected.getRatio()).isEqualTo(wheelDrive.getRatio());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(wheelDrive.getImgUrl());
		});
	}

	@Test
	@DisplayName("선택 가능한 구동방식 목록이 존재하지 않습니다")
	void noExistSelectableWheelDrives() {
		// given
		WheelDrive wheelDrive = WheelDrive.builder()
			.id(1L)
			.name("2WD")
			.description("2WD Description")
			.price(0)
			.ratio(22)
			.imgUrl("imgUrl")
			.build();
		given(getWheelDrivePort.findSelectableWheelDrivesByTrimId(any())).willReturn(Arrays.asList(wheelDrive));

		given(getWheelDrivePort.findUnselectableWheelDriveIdsByEngineId(any())).willReturn(
			Arrays.asList(1L, 3L));

		// when
		GetWheelDrivesResponse getWheelDrivesResponse = getWheelDrivesUseCase.execute(1L, 1L);

		// then
		List<WheelDriveDto> wheelDrives = getWheelDrivesResponse.getWheelDrives();

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(wheelDrives).isNotNull();
			softAssertions.assertThat(wheelDrives).hasSize(0);
		});
	}
}
