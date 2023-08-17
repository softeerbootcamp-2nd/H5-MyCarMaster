package softeer.be_my_car_master.api.car_master.dto.request;

public enum FilterEnum {

	SALES("sales"), DISTANCE("distance"),;

	private String value;

	FilterEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
