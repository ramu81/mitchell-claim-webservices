package com.mitchell.examples.claim.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

public class MitchellUtil {

	private static final Logger logger = Logger.getLogger(MitchellUtil.class);

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
				logger.info("  xmlCalendar ==> " + xmlCalendar);
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
		logger.info("  calendar ==> " + calendar);
		return calendar.toGregorianCalendar().getTime();
	}

}
