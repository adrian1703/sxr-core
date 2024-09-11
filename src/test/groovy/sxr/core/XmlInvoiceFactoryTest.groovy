package sxr.core

import spock.lang.Specification
import sxr.model.entities.Invoice

class XmlInvoiceFactoryTest extends Specification {
    XmlInvoiceFactory xmlInvoiceFactory

    void setup() {
        xmlInvoiceFactory = new XmlInvoiceFactory()
    }

    def "CreateInvoice"() {
        given:
        def input = "not needed yet"
        when:
        def result = xmlInvoiceFactory.createInvoice(input)
        then:
        assert result instanceof Invoice

    }

    def "CreateInvoice2"() {
        given:
        def input = "not needed yet"
        when:
        def result = xmlInvoiceFactory.createInvoice(input)
        then:
        assert result instanceof Invoice

    }
}
