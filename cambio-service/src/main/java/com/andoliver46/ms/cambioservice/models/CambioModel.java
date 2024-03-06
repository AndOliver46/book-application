package com.andoliver46.ms.cambioservice.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity(name = "cambio")
public class CambioModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "from_currency", nullable = false, length = 3)
	private String from;
	@Column(name = "to_currency", nullable = false, length = 3)
	private String to;
	@Column(nullable = false)
	private BigDecimal conversionFactor;
	@Transient
	private BigDecimal convertedValue;
	@Transient
	private String environment;
	
	public CambioModel() {}
	
	public CambioModel(Long id, String currencyFrom, String currencyTo, BigDecimal conversionFactor,
			BigDecimal conversionValue, String environment) {
		super();
		this.id = id;
		this.from = currencyFrom;
		this.to = currencyTo;
		this.conversionFactor = conversionFactor;
		this.convertedValue = conversionValue;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyFrom() {
		return from;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.from = currencyFrom;
	}

	public String getCurrencyTo() {
		return to;
	}

	public void setCurrencyTo(String currencyTo) {
		this.to = currencyTo;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public BigDecimal getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public void calculate(BigDecimal amount) {
		this.convertedValue = this.conversionFactor.multiply(amount);
		this.convertedValue.setScale(2, RoundingMode.CEILING);
	}

	@Override
	public int hashCode() {
		return Objects.hash(conversionFactor, convertedValue, from, to, environment, id);
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
				&& Objects.equals(convertedValue, other.convertedValue)
				&& Objects.equals(from, other.from) && Objects.equals(to, other.to)
				&& Objects.equals(environment, other.environment) && Objects.equals(id, other.id);
	}
	
	

}
