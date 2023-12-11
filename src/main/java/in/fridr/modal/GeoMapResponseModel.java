package in.fridr.modal;

import java.util.List;

public class GeoMapResponseModel {
	
	private List<String> latitude;
	private List<String> longitude;
	private List<String> cityName;
	private List<Long> pop;
	public GeoMapResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeoMapResponseModel(List<String> latitude, List<String> longitude, List<String> cityName) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.cityName = cityName;
	}

	public List<String> getLatitude() {
		return latitude;
	}

	public void setLatitude(List<String> latitude) {
		this.latitude = latitude;
	}

	public List<String> getLongitude() {
		return longitude;
	}

	public void setLongitude(List<String> longitude) {
		this.longitude = longitude;
	}

	public List<String> getCityName() {
		return cityName;
	}

	public void setCityName(List<String> cityName) {
		this.cityName = cityName;
	}

	public List<Long> getPop() {
		return pop;
	}

	public void setPop(List<Long> pop) {
		this.pop = pop;
	}
	

}
