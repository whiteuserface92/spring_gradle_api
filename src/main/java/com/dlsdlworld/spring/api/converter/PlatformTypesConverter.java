package com.dlsdlworld.spring.api.converter;

import com.dlsdlworld.spring.api.types.PlatformTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * ProjectType 변환기
 */
@Converter(autoApply = true)
public class PlatformTypesConverter implements AttributeConverter<PlatformTypes, String> {

	@Override
	public String convertToDatabaseColumn(PlatformTypes platformType) {
		if (platformType == null)
			return null;

		return platformType.getCode();
	}

	@Override
	public PlatformTypes convertToEntityAttribute(String code) {
		if (code == null)
			return null;

		return PlatformTypes.codeOf(code);
	}
}
