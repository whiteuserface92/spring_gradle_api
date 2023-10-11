package com.dlsdlworld.spring.api.converter;

import com.dlsdlworld.spring.api.types.PermissionTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * PermissionType 변환기
 */
@Converter(autoApply = true)
public class PermissionTypeConverter implements AttributeConverter<PermissionTypes, String> {

	@Override
	public String convertToDatabaseColumn(PermissionTypes permissionType) {
		if (permissionType == null)
			return null;

		return permissionType.getCode();
	}

	@Override
	public PermissionTypes convertToEntityAttribute(String code) {
		if (code == null)
			return null;

		return PermissionTypes.codeOf(code);
	}
}
