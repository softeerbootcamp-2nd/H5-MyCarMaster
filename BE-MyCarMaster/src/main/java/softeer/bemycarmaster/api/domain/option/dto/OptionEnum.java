package softeer.bemycarmaster.api.domain.option.dto;

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
