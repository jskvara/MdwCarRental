package carrental.model.entity;

import javax.persistence.Id;

public class AddressEntity implements IEntity {

	@Id
	private Long id;
	private String city;
	private String country;
	private String postalNumber;
	private String street;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalNumber() {
		return postalNumber;
	}

	public void setPostalNumber(String postalNumber) {
		this.postalNumber = postalNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}