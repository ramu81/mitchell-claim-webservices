package com.mitchell.examples.claim.util;

import java.lang.reflect.Field;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.mitchell.examples.claim.CauseOfLossCode;
import com.mitchell.examples.claim.StatusCode;
import com.mitchell.examples.claim.constants.CommonConstants;

public class BeanUtils {

	public BeanUtils() {
	}

	private static final Logger logger = Logger.getLogger(BeanUtils.class);

	public static Object copyProperties(Object source, Object destination) {
		Class<? extends Object> sourceClass = source.getClass();
		Class<? extends Object> destClass = destination.getClass();
		for (Field field : destClass.getFields()) {
			field.setAccessible(true);
		}
		for (Field sourceField : sourceClass.getDeclaredFields()) {
			try {
				if (!sourceField.getName().equalsIgnoreCase(
						CommonConstants.S_ID)) {
					sourceField.setAccessible(true);
					String sourceType = sourceField.getType().getName();
					Field destFeild = destClass.getDeclaredField(sourceField
							.getName());
					String destType = destFeild.getType().getName();
					Object value = sourceField.get(source);
					destFeild.setAccessible(true);
					if (value != null) {
						if (sourceType.equals(CommonConstants.STRING)
								|| sourceType.equals(CommonConstants.LONG)
								|| sourceType.equals(CommonConstants.INTEGER)
								|| sourceType.equals(CommonConstants.DATE)
								|| sourceField.getType().isPrimitive()) {

							if (destType.equals(CommonConstants.XML_DATE)) {
								destFeild.set(destination, MitchellUtil
										.toXMLGregorianCalendar((Date) value));
							} else if (destType.equals(CommonConstants.STRING)
									|| destType.equals(CommonConstants.INTEGER)
									|| destType.equals(CommonConstants.LONG)
									|| destType.equals(CommonConstants.DATE)
									|| sourceField.getType().isPrimitive()) {
								destFeild.set(destination, value);
							} else if (destFeild.getType().isEnum()) {
								if (destType
										.equals(CommonConstants.STATUS_CODE)) {
									destFeild.set(destination, StatusCode
											.fromValue(value.toString()));
								} else if (destType
										.equals(CommonConstants.CAUSE_CODE)) {
									destFeild.set(destination, CauseOfLossCode
											.fromValue(value.toString()));
								}
							}
						} else if (sourceType.equals(CommonConstants.XML_DATE)) {
							destFeild.set(destination, MitchellUtil
									.toDate((XMLGregorianCalendar) value));

						} else if (sourceField.getType().isEnum()) {
							if (sourceType.equals(CommonConstants.CAUSE_CODE)) {
								destFeild.set(destination,
										((CauseOfLossCode) value).value());
							} else if (sourceType
									.equals(CommonConstants.STATUS_CODE)) {
								destFeild.set(destination,
										((StatusCode) value).value());
							}
						}
					}
				}
			} catch (Exception e) {

				logger.fatal("Method :  copyProperties  :  " + e);

			}
		}
		return destination;
	}
}
