package com.mitchell.examples.claim.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.mitchell.examples.claim.constants.CommonConstants;

public class MitchellUtil {

	private static final Logger logger = Logger.getLogger(MitchellUtil.class);

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static String[] getNullProperties(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static String[] getIgnoreProperties(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyType(pd.getName());
			if (srcValue instanceof Enum || srcValue instanceof Date
					|| srcValue instanceof XMLGregorianCalendar)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/*
	 * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		if (date != null) {
			GregorianCalendar gCalendar = new GregorianCalendar();
			gCalendar.setTime(date);
			XMLGregorianCalendar xmlCalendar = null;
			try {
				xmlCalendar = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(gCalendar);
			} catch (DatatypeConfigurationException ex) {
				logger.fatal("Method :  toXMLGregorianCalendar  :  " + ex);
			}
			return xmlCalendar;
		} else {
			return null;
		}
	}

	/*
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 */
	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	/**
	 * This method is used for to get enum string based on it's enum code
	 * 
	 * @param <T>
	 * @param clazz
	 * @param t
	 * @return
	 */
	public static <T extends Enum<T>> String getEnumStrVal(Class<T> clazz,
			Enum<T> t) {

		if (t == null || t.toString().equalsIgnoreCase(CommonConstants.NULL))
			return null;

		String enumStr = null;
		T tempT = findEnumConstant(clazz, t.toString());

		if (tempT != null) {
			enumStr = tempT.toString();
		} else {
			logger.error("NFUtil : getEnumStrVal : No Enum is found for code :"
					+ t);
		}

		return enumStr;
	}

	/**
	 * This method is used for to get enum code based on the string
	 * 
	 * @param <T>
	 * @param clazz
	 * @param s
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumCode(Class<T> clazz, String s) {

		T t = null;

		if (s == null || s.isEmpty())
			s = CommonConstants.NULL;

		t = findEnumConstant(clazz, s);

		if (t == null && s != CommonConstants.NULL) {
			logger.error("NFUtil : getEnumCode : No Enum is found for code :"
					+ s);
			t = findEnumConstant(clazz, CommonConstants.NULL);
		}

		return t;
	}

	/**
	 * This method is used to find a value in enum
	 * 
	 * @param <T>
	 * @param clazz
	 * @param s
	 * @return
	 */
	private static <T extends Enum<T>> T findEnumConstant(Class<T> clazz,
			String s) {
		T t = null;
		for (T e : clazz.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				t = e;
				break;
			}
		}
		return t;
	}
}
