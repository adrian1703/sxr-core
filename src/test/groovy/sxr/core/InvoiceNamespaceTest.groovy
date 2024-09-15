package sxr.core

import groovy.namespace.QName
import spock.lang.Specification

class InvoiceNamespaceTest extends Specification {
    def "GetPrefix"() {
    }

    def "QNameToPrefixAndName"() {
        given:
        List qNames = [
                new QName("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"                    , "Invoice"      , "ubl")
                , new QName("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2", "InvoicePeriod", "cac")
                , new QName("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"    , "StartDate"    , "cbc")
        ]
        when:
        def result = InvoiceNamespace.qNameToPrefixAndName(qNames)
        then:
        assert result[0] == "ubl:Invoice"
        assert result[1] == "cac:InvoicePeriod"
        assert result[2] == "cbc:StartDate"

    }
}
