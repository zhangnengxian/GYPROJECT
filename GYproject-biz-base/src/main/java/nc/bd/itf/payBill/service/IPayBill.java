/**
 * IPayBill.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nc.bd.itf.payBill.service;

public interface IPayBill extends javax.xml.rpc.Service {
    public java.lang.String getIPayBillSOAP11port_httpAddress();

    public nc.bd.itf.payBill.service.IPayBillPortType getIPayBillSOAP11port_http() throws javax.xml.rpc.ServiceException;

    public nc.bd.itf.payBill.service.IPayBillPortType getIPayBillSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
