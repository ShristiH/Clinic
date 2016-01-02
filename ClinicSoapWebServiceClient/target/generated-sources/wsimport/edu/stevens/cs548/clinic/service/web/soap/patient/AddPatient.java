
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addPatient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPatient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cs548.stevens.edu/clinic/dto}patient-dto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPatient", propOrder = {
    "patientDto"
})
public class AddPatient {

    @XmlElement(name = "patient-dto", namespace = "http://cs548.stevens.edu/clinic/dto")
    protected PatientDto patientDto;

    /**
     * Gets the value of the patientDto property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDto }
     *     
     */
    public PatientDto getPatientDto() {
        return patientDto;
    }

    /**
     * Sets the value of the patientDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDto }
     *     
     */
    public void setPatientDto(PatientDto value) {
        this.patientDto = value;
    }

}
