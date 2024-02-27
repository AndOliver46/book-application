package com.andoliver46.ms.cambioservice.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cambio")
public class CambioModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 3)
	private String currencyFrom;
	@Column(nullable = false, length = 3)
	private String currencyTo;
	@Column(nullable = false)
	private BigDecimal conversionFactor;
	@Transient
	private BigDecimal conversionValue;
	@Transient
	private String environment;
	
	public CambioModel() {}
	
	public CambioModel(Long id, String currencyFrom, String currencyTo, BigDecimal conversionFactor,
			BigDecimal conversionValue, String environment) {
		super();
		this.id = id;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.conversionFactor = conversionFactor;
		this.conversionValue = conversionValue;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public BigDecimal getConversionValue() {
		return conversionValue;
	}

	public void setConversionValue(BigDecimal conversionValue) {
		this.conversionValue = conversionValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conversionFactor, conversionValue, currencyFrom, currencyTo, environment, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CambioModel other = (CambioModel) obj;
		return Objects.equals(conversionFactor, other.conversionFactor)
				&& Objects.equals(conversionValue, other.conversionValue)
				&& Objects.equals(currencyFrom, other.currencyFrom) && Objects.equals(currencyTo, other.currencyTo)
				&& Objects.equals(environment, other.environment) && Objects.equals(id, other.id);
	}
	
	

}
