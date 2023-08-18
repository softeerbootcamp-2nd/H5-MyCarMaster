package softeer.be_my_car_master.api.agency.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.api.car_master.dto.response.CarMasterAgencyDto;
import softeer.be_my_car_master.domain.car_master.CarMaster;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarMasterInAgencyDto {

	@Schema(description = "카마스터 식별자", example = "1")
	private Long id;

	@Schema(description = "카마스터 이름", example = "이몽룡")
	private String name;

	@Schema(description = "카마스터 사진", example = "imgUrl")
	private String imgUrl;

	@Schema(description = "소개말", example = "안녕하십니까 하하하")
	private String intro;

	@Schema(description = "전화번호", example = "010-0000-0000")
	private String phone;

	private CarMasterAgencyDto agency;

	public static CarMasterInAgencyDto from(CarMaster carMaster) {
		CarMasterAgencyDto carMasterAgencyDto =
			new CarMasterAgencyDto(carMaster.getAgencyId(), carMaster.getAgencyName());

		return CarMasterInAgencyDto.builder()
			.id(carMaster.getId())
			.name(carMaster.getName())
			.imgUrl(carMaster.getImgUrl())
			.intro(carMaster.getIntro())
			.phone(carMaster.getPhone())
			.agency(carMasterAgencyDto)
			.build();
	}
}
