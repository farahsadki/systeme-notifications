package com.xyz.product.delivery.notification.api.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Address {

	@Id
	@Column
	private String customerId;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String address3;
	@Column
	private String city;
	@Column
	private String pinCode;
	@Column
	private String state;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return customerId.equals(address.customerId) &&
				address1.equals(address.address1) &&
				address2.equals(address.address2) &&
				Objects.equals(address3, address.address3) &&
				city.equals(address.city) &&
				pinCode.equals(address.pinCode) &&
				Objects.equals(state, address.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, address1, address2, address3, city, pinCode, state);
	}

	@Override
	public String toString() {
		return "Address{" +
				"customerId='" + customerId + '\'' +
				", address1='" + address1 + '\'' +
				", address2='" + address2 + '\'' +
				", address3='" + address3 + '\'' +
				", city='" + city + '\'' +
				", pinCode='" + pinCode + '\'' +
				", state='" + state + '\'' +
				'}';
	}
}
