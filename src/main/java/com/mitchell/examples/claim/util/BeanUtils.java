package com.mitchell.examples.claim.util;

import java.lang.reflect.Field;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.services.repo.model.MitchellClaim;

public class BeanUtils {

	public BeanUtils() {
	}

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException {
		copyProperties(new MitchellClaimType(), new MitchellClaim());
	}

	public static Object copyProperties(Object source, Object destination)
			throws NoSuchFieldException, SecurityException {
		Class<? extends Object> sClass = source.getClass();
		Class<? extends Object> dClass = destination.getClass();
		for (Field field : dClass.getFields()) {
			field.setAccessible(true);
		}
		for (Field field : sClass.getDeclaredFields()) {
			try {
				if (!field.getName().equalsIgnoreCase("serialVersionUID")) {
					field.setAccessible(true);
					Class<?> class1 = field.getType();
					Field field2 = dClass.getDeclaredField(field.getName());
					Object value = field.get(source);;
					field2.setAccessible(true);
					
					if (class1.equals(String.class)
							|| class1.equals(Date.class)
							|| class1.equals(Long.class)) {
						
						if (field.get(source) != null){
							if(field2.getType().equals(javax.xml.datatype.XMLGregorianCalendar.class)){
								Date date = (Date) value;field2.set(value, destination);
							}else {
							field2.set(value, destination);
							}
						}
					} else if (class1
							.equals(javax.xml.datatype.XMLGregorianCalendar.class)) {
						if (field.get(source) != null){
							XMLGregorianCalendar date = (XMLGregorianCalendar) value;
							field2.set(date, destination);
						}
					} else if (class1
							.equals(com.mitchell.examples.claim.CauseOfLossCode.class)) {
						if (field.get(source) != null)
							field2.set(field.get(source), destination);
					} else if (class1
							.equals(com.mitchell.examples.claim.StatusCode.class)) {
						if (field.get(source) != null)
							field2.set(field.get(source), destination);
					} else {
						System.out.println(class1);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return destination;
	}
}
