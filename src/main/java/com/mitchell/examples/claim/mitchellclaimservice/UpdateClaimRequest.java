//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.01 at 12:16:33 AM EDT 
//


package com.mitchell.examples.claim.mitchellclaimservice;

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
 *         &lt;element name="MitchellClaim" type="{http://www.mitchell.com/examples/claim}MitchellClaimType"/>
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
    "mitchellClaim"
})
@XmlRootElement(name = "UpdateClaimRequest")
public class UpdateClaimRequest {

    @XmlElement(name = "MitchellClaim", required = true)
    protected MitchellClaimType mitchellClaim;

    /**
     * Gets the value of the mitchellClaim property.
     * 
     * @return
     *     possible object is
     *     {@link MitchellClaimType }
     *     
     */
    public MitchellClaimType getMitchellClaim() {
        return mitchellClaim;
    }

    /**
     * Sets the value of the mitchellClaim property.
     * 
     * @param value
     *     allowed object is
     *     {@link MitchellClaimType }
     *     
     */
    public void setMitchellClaim(MitchellClaimType value) {
        this.mitchellClaim = value;
    }

}
