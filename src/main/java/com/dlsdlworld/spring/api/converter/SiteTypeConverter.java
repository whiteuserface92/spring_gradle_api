package com.dlsdlworld.spring.api.converter;

import com.dlsdlworld.spring.api.types.SiteTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * SiteType 변환기
 */
@Converter(autoApply = true)
public class SiteTypeConverter implements AttributeConverter<SiteTypes, String> {

	@Override
	public String convertToDatabaseColumn(SiteTypes siteType) {
		if (siteType == null)
			return null;

		return siteType.getCode();
	}

	@Override
	public SiteTypes convertToEntityAttribute(String code) {
		if (code == null)
			return null;

		return SiteTypes.codeOf(code);
	}
}
