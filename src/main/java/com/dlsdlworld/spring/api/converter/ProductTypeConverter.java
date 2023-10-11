package com.dlsdlworld.spring.api.converter;

import com.dlsdlworld.spring.api.types.ProductTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * ProjectType 변환기
 */
@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductTypes, String> {

	@Override
	public String convertToDatabaseColumn(ProductTypes prodType) {
		if (prodType == null)
			return null;

		return prodType.getCode();
	}

	@Override
	public ProductTypes convertToEntityAttribute(String code) {
		if (code == null)
			return null;

		return ProductTypes.codeOf(code);
	}
}
