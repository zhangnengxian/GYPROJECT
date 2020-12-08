/**
 * BusinessException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package nc.bd.itf.customer.service;

public class BusinessException  extends nc.bd.itf.customer.service.Exception  implements java.io.Serializable {
    private java.lang.String errorCodeString;

    private java.lang.String hint;

    public BusinessException() {
    }

    public BusinessException(
           java.lang.String message1,
           java.lang.String[] stackTrace1,
           java.lang.String errorCodeString,
           java.lang.String hint) {
        super(
            message1,
            stackTrace1);
        this.errorCodeString = errorCodeString;
        this.hint = hint;
    }


    /**
     * Gets the errorCodeString value for this BusinessException.
     * 
     * @return errorCodeString
     */
    public java.lang.String getErrorCodeString() {
        return errorCodeString;
    }


    /**
     * Sets the errorCodeString value for this BusinessException.
     * 
     * @param errorCodeString
     */
    public void setErrorCodeString(java.lang.String errorCodeString) {
        this.errorCodeString = errorCodeString;
    }


    /**
     * Gets the hint value for this BusinessException.
     * 
     * @return hint
     */
    public java.lang.String getHint() {
        return hint;
    }


    /**
     * Sets the hint value for this BusinessException.
     * 
     * @param hint
     */
    public void setHint(java.lang.String hint) {
        this.hint = hint;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessException)) return false;
        BusinessException other = (BusinessException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.errorCodeString==null && other.getErrorCodeString()==null) || 
             (this.errorCodeString!=null &&
              this.errorCodeString.equals(other.getErrorCodeString()))) &&
            ((this.hint==null && other.getHint()==null) || 
             (this.hint!=null &&
              this.hint.equals(other.getHint())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getErrorCodeString() != null) {
            _hashCode += getErrorCodeString().hashCode();
        }
        if (getHint() != null) {
            _hashCode += getHint().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pub.vo.nc/BusinessException", "BusinessException"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCodeString");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorCodeString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hint");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
