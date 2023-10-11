package com.dlsdlworld.spring.api.converter;

import com.dlsdlworld.spring.api.types.JoinTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JoinType 변환기
 */
@Converter(autoApply = true)
public class JoinTypeConverter implements AttributeConverter<JoinTypes, String> {

	@Override
	public String convertToDatabaseColumn(JoinTypes joinType) {
		if (joinType == null)
			return null;

		return joinType.getCode();
	}

	@Override
	public JoinTypes convertToEntityAttribute(String code) {
		if (code == null)
			return null;

		return JoinTypes.codeOf(code);
	}
}
