package com.mitchell.examples.claim.util;

import java.lang.reflect.Field;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.mitchell.examples.claim.CauseOfLossCode;
import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.StatusCode;
import com.mitchell.examples.claim.VehicleInfoType;
import com.mitchell.examples.claim.services.repo.model.MitchellClaim;
import com.mitchell.examples.claim.services.repo.model.VehicleInfo;

public class BeanUtils {

	public BeanUtils() {
	}

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException {
		MitchellClaimType claimType = new MitchellClaimType();
		claimType.setStatus(StatusCode.CLOSED);
		;
		;
		System.out.println(copyProperties(claimType, new MitchellClaim()));
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
					if (sourceType.equals("java.lang.String")
							|| sourceType.equals(Date.class.toString())
							|| sourceType.equals("java.lang.Long")
							|| sourceType.equals("java.lang.Integer")
							|| sourceType.equals("int")) {

						if (value != null) {
							if (destType
									.equals("javax.xml.datatype.XMLGregorianCalendar")) {
								destFeild.set(destination, value);
							} else if (destType
									.equals("com.mitchell.examples.claim.StatusCode")) {
								destFeild.set(destination,
										StatusCode.fromValue(value.toString()));
							} else if (destType
									.equals("com.mitchell.examples.claim.CauseOfLossCode")) {
								destFeild.set(destination, CauseOfLossCode
										.fromValue(value.toString()));
							} else if (destType.equals("java.lang.String")
									|| destType.equals("java.lang.Integer")
									|| destType.equals("int")) {
								destFeild.set(destination, value);
							}
						}
					} else if (sourceType
							.equals("javax.xml.datatype.XMLGregorianCalendar")) {
						if (sourceField.get(source) != null)
							destFeild.set(destination, MitchellUtil
									.toDate((XMLGregorianCalendar) value));

					} else if (sourceType
							.equals("com.mitchell.examples.claim.CauseOfLossCode")) {
						if (sourceField.get(source) != null)
							destFeild.set(destination, sourceField.get(source)
									.toString());
					} else if (sourceType
							.equals("com.mitchell.examples.claim.StatusCode")) {
						if (sourceField.get(source) != null)
							destFeild.set(destination, sourceField.get(source)
									.toString());
					} else {
						System.out.println(sourceType);
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
