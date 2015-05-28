package com.mitchell.examples.claim.util;

import java.lang.reflect.Field;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.mitchell.examples.claim.CauseOfLossCode;
import com.mitchell.examples.claim.LossInfoType;
import com.mitchell.examples.claim.StatusCode;
import com.mitchell.examples.claim.services.repo.model.LossInfo;

public class BeanUtils {

	public BeanUtils() {
	}

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException {
		LossInfoType claimType = new LossInfoType();
		claimType.setCauseOfLoss(CauseOfLossCode.EXPLOSION);;
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
				if (!sourceField.getName().equalsIgnoreCase("serialVersionUID")) {
					sourceField.setAccessible(true);
					String sourceType = sourceField.getType().getName();
					Field destFeild = destClass.getDeclaredField(sourceField
							.getName());
					String destType = destFeild.getType().getName();
					Object value = sourceField.get(source);
					destFeild.setAccessible(true);
					if (value != null) {
						if (sourceType.equals("java.lang.String")
								|| sourceType.equals("java.lang.Long")
								|| sourceType.equals("java.lang.Integer")
								|| sourceType.equals("java.util.Date")
								|| sourceType.equals("int")) {

							if (destType
									.equals("javax.xml.datatype.XMLGregorianCalendar")) {
								destFeild.set(destination, MitchellUtil
										.toXMLGregorianCalendar((Date) value));
							} else if (destType
									.equals("com.mitchell.examples.claim.StatusCode")) {
								destFeild.set(destination,
										StatusCode.fromValue(value.toString()));
							} else if (destType
									.equals("com.mitchell.examples.claim.CauseOfLossCode")) {
								destFeild.set(destination, CauseOfLossCode
										.fromValue(value.toString()));
								System.out.println(destFeild.get(destination));
							} else if (destType.equals("java.lang.String")
									|| destType.equals("java.lang.Integer")
									|| destType.equals("int")) {
								destFeild.set(destination, value);
							}
						} else if (sourceType
								.equals("javax.xml.datatype.XMLGregorianCalendar")) {
							destFeild.set(destination, MitchellUtil
									.toDate((XMLGregorianCalendar) value));

						} else if (sourceType
								.equals("com.mitchell.examples.claim.CauseOfLossCode")) {
							destFeild.set(destination,((CauseOfLossCode) value).value());
						} else if (sourceType
								.equals("com.mitchell.examples.claim.StatusCode")) {
							destFeild.set(destination,((StatusCode) value).value());
						} else {
							System.out.println(sourceField.getType());
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
			}
		}
		return destination;
	}
}
