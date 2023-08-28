package softeer.be_my_car_master.domain.option;

import lombok.Getter;

@Getter
public enum Category {

	SAFE("안전"),
	STYLE_AND_PERFORMANCE("스타일&퍼포먼스"),
	CAR_PROTECTION("차량 보호"),
	CONVENIENCE("편의"),
	POWER_TRAIN_PERFORMANCE("파워트레인/성능"),
	INTELLIGENT_SAFETY_TECHNOLOGY("지능형 안전기술"),
	EXTERNAL("외관"),
	INTERNAL("내장"),
	SEAT("시트"),
	MULTIMEDIA("멀티미디어");

	private String value;

	Category(String value) {
		this.value = value;
	}
}
