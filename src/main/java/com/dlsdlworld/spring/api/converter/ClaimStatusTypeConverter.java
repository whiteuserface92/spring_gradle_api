package com.dlsdlworld.spring.api.converter;

import com.dlsdlworld.spring.api.types.ClaimStatusTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * ClaimStatusType 변환기
 */
@Converter(autoApply = true)
public class ClaimStatusTypeConverter implements AttributeConverter<ClaimStatusTypes, String> {

	@Override
	public String convertToDatabaseColumn(ClaimStatusTypes claimStatusTypes) {
		if (claimStatusTypes == null)
			return null;

		return claimStatusTypes.getCode();
	}

	@Override
	public ClaimStatusTypes convertToEntityAttribute(String code) {
		if (code == null)
			return null;

		return ClaimStatusTypes.codeOf(code);
	}
}
