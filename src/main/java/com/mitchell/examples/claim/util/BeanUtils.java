package com.mitchell.examples.claim.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.mitchell.examples.claim.CauseOfLossCode;
import com.mitchell.examples.claim.CommonConstants;
import com.mitchell.examples.claim.LossInfoType;
import com.mitchell.examples.claim.StatusCode;
import com.mitchell.examples.claim.services.repo.model.LossInfo;

public class BeanUtils {

	public BeanUtils() {
	}

	public static void main(String[] args) {
		LossInfoType claimType = new LossInfoType();
		claimType.setCauseOfLoss(CauseOfLossCode.COLLISION);
		System.out.println(copyProperties(claimType, new LossInfo()));
	}

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
								|| sourceType.equals(CommonConstants.INT)) {

							if (destType.equals(CommonConstants.XML_DATE)) {
								destFeild.set(destination, MitchellUtil
										.toXMLGregorianCalendar((Date) value));
							} else if (destType
									.equals(CommonConstants.STATUS_CODE)) {
								destFeild.set(destination,
										StatusCode.fromValue(value.toString()));
							} else if (destType
									.equals(CommonConstants.CAUSE_CODE)) {
								destFeild.set(destination, CauseOfLossCode
										.fromValue(value.toString()));
								System.out.println(destFeild.get(destination));
							} else if (destType.equals(CommonConstants.STRING)
									|| destType.equals(CommonConstants.INTEGER)
									|| destType.equals(CommonConstants.INT)) {
								destFeild.set(destination, value);
							}
						} else if (sourceType.equals(CommonConstants.XML_DATE)) {
							destFeild.set(destination, MitchellUtil
									.toDate((XMLGregorianCalendar) value));

						} else if (sourceField.getType().isEnum()) {
							Class<?> enumClazz = sourceField.getType();
							Method m = enumClazz.getMethod("value", null);
							destFeild.set(destination, value.toString());
							System.out.println(value);
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return destination;
	}
}
