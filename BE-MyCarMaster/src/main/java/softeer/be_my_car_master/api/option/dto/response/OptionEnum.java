package softeer.be_my_car_master.api.option.dto.response;

public enum OptionEnum {

	SAFE("안전"),
	STYLE_AND_PERFORMANCE("스타일&퍼포먼스"),
	CAR_PROTECTION("차량 보호"),
	CONVENIENCE("편의");

	private String name;

	OptionEnum(String name) {
		this.name = name;
	}
}
