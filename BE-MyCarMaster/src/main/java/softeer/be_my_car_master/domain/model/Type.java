package softeer.be_my_car_master.domain.model;

import lombok.Getter;

@Getter
public enum Type {

	HYDROGEN_ELECTRIC("수소/전기차"),
	N("N"),
	PASSENGER("승용차"),
	SUV("SUV"),
	MPV("MPV"),
	LIGHT_TRUCK_AND_TAXI("소형 트럭&택시"),
	TRUCK("트럭"),
	BUS("버스");

	private String value;

	Type(String value) {
		this.value = value;
	}
}
