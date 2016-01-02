
package edu.stevens.cs548.clinic.service.web.soap.provider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProviderByProIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProviderByProIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cs548.stevens.edu/clinic/dto}provider-dto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProviderByProIdResponse", propOrder = {
    "providerDto"
})
public class GetProviderByProIdResponse {

    @XmlElement(name = "provider-dto", namespace = "http://cs548.stevens.edu/clinic/dto")
    protected ProviderDto providerDto;

    /**
     * Gets the value of the providerDto property.
     * 
     * @return
     *     possible object is
     *     {@link ProviderDto }
     *     
     */
    public ProviderDto getProviderDto() {
        return providerDto;
    }

    /**
     * Sets the value of the providerDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderDto }
     *     
     */
    public void setProviderDto(ProviderDto value) {
        this.providerDto = value;
    }

}
