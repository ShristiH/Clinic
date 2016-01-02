
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "TreatmentNotFoundExn", targetNamespace = "http://cs548.stevens.edu/clinic/service/web/soap")
public class TreatmentNotFoundExn_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private TreatmentNotFoundExn faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public TreatmentNotFoundExn_Exception(String message, TreatmentNotFoundExn faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public TreatmentNotFoundExn_Exception(String message, TreatmentNotFoundExn faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: edu.stevens.cs548.clinic.service.web.soap.patient.TreatmentNotFoundExn
     */
    public TreatmentNotFoundExn getFaultInfo() {
        return faultInfo;
    }

}
