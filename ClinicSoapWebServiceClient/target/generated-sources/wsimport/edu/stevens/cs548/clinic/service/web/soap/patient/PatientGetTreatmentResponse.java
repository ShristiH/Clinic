
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for patientGetTreatmentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="patientGetTreatmentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cs548.stevens.edu/clinic/dto}treatment-dto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientGetTreatmentResponse", propOrder = {
    "treatmentDto"
})
public class PatientGetTreatmentResponse {

    @XmlElement(name = "treatment-dto", namespace = "http://cs548.stevens.edu/clinic/dto")
    protected TreatmentDto treatmentDto;

    /**
     * Gets the value of the treatmentDto property.
     * 
     * @return
     *     possible object is
     *     {@link TreatmentDto }
     *     
     */
    public TreatmentDto getTreatmentDto() {
        return treatmentDto;
    }

    /**
     * Sets the value of the treatmentDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link TreatmentDto }
     *     
     */
    public void setTreatmentDto(TreatmentDto value) {
        this.treatmentDto = value;
    }

}
