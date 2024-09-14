package sxr.core

import spock.lang.Specification
import sxr.model.entities.invoice.Invoice

class XmlInvoiceFactoryTest extends Specification {
    XmlInvoiceFactory xmlInvoiceFactory

    void setup() {
        xmlInvoiceFactory = new XmlInvoiceFactory()
    }

    def deserializeInvoice() {
        given:
        def input = "not needed yet"
        when:
        def result = xmlInvoiceFactory.deserializeInvoice(input)
        then:
        assert result instanceof Invoice

    }

    def deserializeInvoice2() {
        given:
        def input = "not needed yet"
        when:
        def result = xmlInvoiceFactory.deserializeInvoice(input)
        then:
        assert result instanceof Invoice

    }
}
