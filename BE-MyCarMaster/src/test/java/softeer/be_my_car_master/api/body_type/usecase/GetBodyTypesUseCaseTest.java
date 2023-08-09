package softeer.be_my_car_master.api.body_type.usecase;

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

import softeer.be_my_car_master.api.body_type.dto.response.BodyTypeDto;
import softeer.be_my_car_master.api.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.api.body_type.usecase.port.BodyTypePort;
import softeer.be_my_car_master.domain.body_type.BodyType;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetBodyTypesUseCase Test")
class GetBodyTypesUseCaseTest {

	@InjectMocks
	private GetBodyTypesUseCase getBodyTypesUseCase;

	@Mock
	private BodyTypePort bodyTypePort;

	@Test
	@DisplayName("바디타입 목록을 조회합니다")
	void execute() {
		// given
		BodyType bodyType = BodyType.builder()
			.id(1L)
			.name("7인승")
			.description("7인승 Description")
			.price(0)
			.ratio(22)
			.imgUrl("imgUrl")
			.build();
		given(bodyTypePort.findSelectableBodyTypesByModelId(any())).willReturn(Arrays.asList(bodyType));

		// when
		GetBodyTypesResponse getBodyTypesResponse = getBodyTypesUseCase.execute(1L);

		// then
		List<BodyTypeDto> bodyTypes = getBodyTypesResponse.getBodyTypes();
		BodyTypeDto expected = bodyTypes.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(bodyTypes).isNotNull();
			softAssertions.assertThat(bodyTypes).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(bodyType.getId());
			softAssertions.assertThat(expected.getName()).isEqualTo(bodyType.getName());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(bodyType.getImgUrl());
		});
	}
}
