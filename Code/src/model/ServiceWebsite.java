package model;

public class ServiceWebsite {
	private String Url;
	private String Name;
	private Integer Order;
	private Double SizeLimit;
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getOrder() {
		return Order;
	}
	public void setOrder(Integer order) {
		Order = order;
	}
	public Double getSizeLimit() {
		return SizeLimit;
	}
	public void setSizeLimit(Double sizeLimit) {
		SizeLimit = sizeLimit;
	}
}
