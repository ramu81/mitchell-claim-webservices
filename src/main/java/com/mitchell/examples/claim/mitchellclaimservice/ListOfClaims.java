//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.22 at 03:56:11 PM EDT 
//


package com.mitchell.examples.claim.mitchellclaimservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.mitchell.examples.claim.MitchellClaimType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListOfClaims" type="{http://www.mitchell.com/examples/claim}MitchellClaimType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listOfClaims"
})
@XmlRootElement(name = "ListOfClaims")
public class ListOfClaims {

    @XmlElement(name = "ListOfClaims")
    protected List<MitchellClaimType> listOfClaims;

    /**
     * Gets the value of the listOfClaims property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfClaims property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfClaims().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MitchellClaimType }
     * 
     * 
     */
    public List<MitchellClaimType> getListOfClaims() {
        if (listOfClaims == null) {
            listOfClaims = new ArrayList<MitchellClaimType>();
        }
        return this.listOfClaims;
    }

}
