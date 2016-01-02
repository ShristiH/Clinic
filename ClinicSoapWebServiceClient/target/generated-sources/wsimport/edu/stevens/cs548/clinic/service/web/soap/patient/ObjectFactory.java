
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.stevens.cs548.clinic.service.web.soap.patient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TreatmentNotFoundExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "TreatmentNotFoundExn");
    private final static QName _GetPatientByPatIdResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getPatientByPatIdResponse");
    private final static QName _GetPatientByPatId_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getPatientByPatId");
    private final static QName _GetPatientResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getPatientResponse");
    private final static QName _AddPatient_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "addPatient");
    private final static QName _SiteInfoResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "siteInfoResponse");
    private final static QName _PatientGetTreatment_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "patientGetTreatment");
    private final static QName _AddPatientResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "addPatientResponse");
    private final static QName _SiteInfo_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "siteInfo");
    private final static QName _PatientNotFoundExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "PatientNotFoundExn");
    private final static QName _GetPatient_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getPatient");
    private final static QName _PatientServiceExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "PatientServiceExn");
    private final static QName _PatientGetTreatmentResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "patientGetTreatmentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.stevens.cs548.clinic.service.web.soap.patient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PatientServiceExn }
     * 
     */
    public PatientServiceExn createPatientServiceExn() {
        return new PatientServiceExn();
    }

    /**
     * Create an instance of {@link PatientGetTreatmentResponse }
     * 
     */
    public PatientGetTreatmentResponse createPatientGetTreatmentResponse() {
        return new PatientGetTreatmentResponse();
    }

    /**
     * Create an instance of {@link AddPatientResponse }
     * 
     */
    public AddPatientResponse createAddPatientResponse() {
        return new AddPatientResponse();
    }

    /**
     * Create an instance of {@link PatientGetTreatment }
     * 
     */
    public PatientGetTreatment createPatientGetTreatment() {
        return new PatientGetTreatment();
    }

    /**
     * Create an instance of {@link SiteInfo }
     * 
     */
    public SiteInfo createSiteInfo() {
        return new SiteInfo();
    }

    /**
     * Create an instance of {@link PatientNotFoundExn }
     * 
     */
    public PatientNotFoundExn createPatientNotFoundExn() {
        return new PatientNotFoundExn();
    }

    /**
     * Create an instance of {@link GetPatient }
     * 
     */
    public GetPatient createGetPatient() {
        return new GetPatient();
    }

    /**
     * Create an instance of {@link GetPatientResponse }
     * 
     */
    public GetPatientResponse createGetPatientResponse() {
        return new GetPatientResponse();
    }

    /**
     * Create an instance of {@link AddPatient }
     * 
     */
    public AddPatient createAddPatient() {
        return new AddPatient();
    }

    /**
     * Create an instance of {@link SiteInfoResponse }
     * 
     */
    public SiteInfoResponse createSiteInfoResponse() {
        return new SiteInfoResponse();
    }

    /**
     * Create an instance of {@link TreatmentNotFoundExn }
     * 
     */
    public TreatmentNotFoundExn createTreatmentNotFoundExn() {
        return new TreatmentNotFoundExn();
    }

    /**
     * Create an instance of {@link GetPatientByPatId }
     * 
     */
    public GetPatientByPatId createGetPatientByPatId() {
        return new GetPatientByPatId();
    }

    /**
     * Create an instance of {@link GetPatientByPatIdResponse }
     * 
     */
    public GetPatientByPatIdResponse createGetPatientByPatIdResponse() {
        return new GetPatientByPatIdResponse();
    }

    /**
     * Create an instance of {@link TreatmentDto }
     * 
     */
    public TreatmentDto createTreatmentDto() {
        return new TreatmentDto();
    }

    /**
     * Create an instance of {@link DrugTreatmentType }
     * 
     */
    public DrugTreatmentType createDrugTreatmentType() {
        return new DrugTreatmentType();
    }

    /**
     * Create an instance of {@link RadiologyType }
     * 
     */
    public RadiologyType createRadiologyType() {
        return new RadiologyType();
    }

    /**
     * Create an instance of {@link SurgeryType }
     * 
     */
    public SurgeryType createSurgeryType() {
        return new SurgeryType();
    }

    /**
     * Create an instance of {@link PatientDto }
     * 
     */
    public PatientDto createPatientDto() {
        return new PatientDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TreatmentNotFoundExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "TreatmentNotFoundExn")
    public JAXBElement<TreatmentNotFoundExn> createTreatmentNotFoundExn(TreatmentNotFoundExn value) {
        return new JAXBElement<TreatmentNotFoundExn>(_TreatmentNotFoundExn_QNAME, TreatmentNotFoundExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientByPatIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getPatientByPatIdResponse")
    public JAXBElement<GetPatientByPatIdResponse> createGetPatientByPatIdResponse(GetPatientByPatIdResponse value) {
        return new JAXBElement<GetPatientByPatIdResponse>(_GetPatientByPatIdResponse_QNAME, GetPatientByPatIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientByPatId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getPatientByPatId")
    public JAXBElement<GetPatientByPatId> createGetPatientByPatId(GetPatientByPatId value) {
        return new JAXBElement<GetPatientByPatId>(_GetPatientByPatId_QNAME, GetPatientByPatId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getPatientResponse")
    public JAXBElement<GetPatientResponse> createGetPatientResponse(GetPatientResponse value) {
        return new JAXBElement<GetPatientResponse>(_GetPatientResponse_QNAME, GetPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "addPatient")
    public JAXBElement<AddPatient> createAddPatient(AddPatient value) {
        return new JAXBElement<AddPatient>(_AddPatient_QNAME, AddPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "siteInfoResponse")
    public JAXBElement<SiteInfoResponse> createSiteInfoResponse(SiteInfoResponse value) {
        return new JAXBElement<SiteInfoResponse>(_SiteInfoResponse_QNAME, SiteInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientGetTreatment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "patientGetTreatment")
    public JAXBElement<PatientGetTreatment> createPatientGetTreatment(PatientGetTreatment value) {
        return new JAXBElement<PatientGetTreatment>(_PatientGetTreatment_QNAME, PatientGetTreatment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "addPatientResponse")
    public JAXBElement<AddPatientResponse> createAddPatientResponse(AddPatientResponse value) {
        return new JAXBElement<AddPatientResponse>(_AddPatientResponse_QNAME, AddPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "siteInfo")
    public JAXBElement<SiteInfo> createSiteInfo(SiteInfo value) {
        return new JAXBElement<SiteInfo>(_SiteInfo_QNAME, SiteInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientNotFoundExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "PatientNotFoundExn")
    public JAXBElement<PatientNotFoundExn> createPatientNotFoundExn(PatientNotFoundExn value) {
        return new JAXBElement<PatientNotFoundExn>(_PatientNotFoundExn_QNAME, PatientNotFoundExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getPatient")
    public JAXBElement<GetPatient> createGetPatient(GetPatient value) {
        return new JAXBElement<GetPatient>(_GetPatient_QNAME, GetPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientServiceExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "PatientServiceExn")
    public JAXBElement<PatientServiceExn> createPatientServiceExn(PatientServiceExn value) {
        return new JAXBElement<PatientServiceExn>(_PatientServiceExn_QNAME, PatientServiceExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientGetTreatmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "patientGetTreatmentResponse")
    public JAXBElement<PatientGetTreatmentResponse> createPatientGetTreatmentResponse(PatientGetTreatmentResponse value) {
        return new JAXBElement<PatientGetTreatmentResponse>(_PatientGetTreatmentResponse_QNAME, PatientGetTreatmentResponse.class, null, value);
    }

}
