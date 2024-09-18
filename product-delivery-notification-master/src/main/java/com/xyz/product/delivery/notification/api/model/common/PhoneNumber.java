package com.xyz.product.delivery.notification.api.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class PhoneNumber {

	@Id
	@Column
	private String customerId;
	@Column
	private String countryCode;
	@Column
	private String phoneNumber;

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhoneNumber that = (PhoneNumber) o;
		return phoneNumber == that.phoneNumber &&
				customerId.equals(that.customerId) &&
				countryCode.equals(that.countryCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, countryCode, phoneNumber);
	}

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "PhoneNumber{" +
				"customerId='" + customerId + '\'' +
				", countryCode='" + countryCode + '\'' +
				", phoneNumber=" + phoneNumber +
				'}';
	}
}
