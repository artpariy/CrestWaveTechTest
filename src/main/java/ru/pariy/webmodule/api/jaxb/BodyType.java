//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.09.26 at 11:42:41 PM MSK 
//


package ru.pariy.webmodule.api.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BodyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sendPayment" type="{}sendPaymentType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodyType", propOrder = {
    "sendPayment"
})
public class BodyType {

    @XmlElement(required = true)
    protected SendPaymentType sendPayment;

    /**
     * Gets the value of the sendPayment property.
     * 
     * @return
     *     possible object is
     *     {@link SendPaymentType }
     *     
     */
    public SendPaymentType getSendPayment() {
        return sendPayment;
    }

    /**
     * Sets the value of the sendPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link SendPaymentType }
     *     
     */
    public void setSendPayment(SendPaymentType value) {
        this.sendPayment = value;
    }

}