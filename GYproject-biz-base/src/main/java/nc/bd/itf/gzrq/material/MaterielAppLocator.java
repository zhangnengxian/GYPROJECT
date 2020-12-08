/**
 * MaterielAppLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nc.bd.itf.gzrq.material;

import cc.dfsoft.uexpress.common.constant.Constants;

public class MaterielAppLocator extends org.apache.axis.client.Service implements nc.bd.itf.gzrq.material.MaterielApp {

    public MaterielAppLocator() {
    }


    public MaterielAppLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MaterielAppLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MaterielAppSOAP11port_http
    //正式
    //private java.lang.String MaterielAppSOAP11port_http_address = "http://222.85.141.19:63/uapws/service/nc.itf.gzrq.c01.MaterielApp";
    private java.lang.String MaterielAppSOAP11port_http_address =  Constants.NC_HOST+"/uapws/service/nc.itf.gzrq.c01.MaterielApp";

    public java.lang.String getMaterielAppSOAP11port_httpAddress() {
        return MaterielAppSOAP11port_http_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MaterielAppSOAP11port_httpWSDDServiceName = "MaterielAppSOAP11port_http";

    public java.lang.String getMaterielAppSOAP11port_httpWSDDServiceName() {
        return MaterielAppSOAP11port_httpWSDDServiceName;
    }

    public void setMaterielAppSOAP11port_httpWSDDServiceName(java.lang.String name) {
        MaterielAppSOAP11port_httpWSDDServiceName = name;
    }

    public nc.bd.itf.gzrq.material.MaterielAppPortType getMaterielAppSOAP11port_http() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MaterielAppSOAP11port_http_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMaterielAppSOAP11port_http(endpoint);
    }

    public nc.bd.itf.gzrq.material.MaterielAppPortType getMaterielAppSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            nc.bd.itf.gzrq.material.MaterielAppSOAP11BindingStub _stub = new nc.bd.itf.gzrq.material.MaterielAppSOAP11BindingStub(portAddress, this);
            _stub.setPortName(getMaterielAppSOAP11port_httpWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMaterielAppSOAP11port_httpEndpointAddress(java.lang.String address) {
        MaterielAppSOAP11port_http_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (nc.bd.itf.gzrq.material.MaterielAppPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                nc.bd.itf.gzrq.material.MaterielAppSOAP11BindingStub _stub = new nc.bd.itf.gzrq.material.MaterielAppSOAP11BindingStub(new java.net.URL(MaterielAppSOAP11port_http_address), this);
                _stub.setPortName(getMaterielAppSOAP11port_httpWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MaterielAppSOAP11port_http".equals(inputPortName)) {
            return getMaterielAppSOAP11port_http();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://c01.gzrq.itf.nc/MaterielApp", "MaterielApp");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://c01.gzrq.itf.nc/MaterielApp", "MaterielAppSOAP11port_http"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MaterielAppSOAP11port_http".equals(portName)) {
            setMaterielAppSOAP11port_httpEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
