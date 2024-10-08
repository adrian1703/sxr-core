package sxr.core.utils

import groovy.namespace.QName
import spock.lang.Specification
import sxr.core.utils.invoice.InvoiceNamespace
import test.ResourcesUtil

class XmlUtilTest extends Specification {

    def "getPrefix"() {
        given:
        String uri = inputUri
        when:
        String prefix = InvoiceNamespace.GetPrefix(uri)
        then:
        assert prefix == expectedPrefix

        where:
        inputUri                                                                   | expectedPrefix
        "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"                   | "ubl"
        "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" | "cac"
        "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"     | "cbc"

    }

    def "getXmlTermPath"() {
        given:
        String pathToTestXml = "ubl_test.xml"
        String nodeName = nodeNameInput as String
        List<String> termPathExpected = termPathExpectedInput as List<String>
        when:
        String xml = ResourcesUtil.readTextFilesFromResources(pathToTestXml)
        Node node = XmlUtil.parseTextToNode(xml)
        node = node.'**'.find { it.name() == nodeName } as Node
        List<QName> termPath     = XmlUtil.getXmlTermPath(node)
        List<String> prefixNames = InvoiceNamespace.QNameToPrefixAndName(termPath)
        then:
        assert termPath.size() == termPathExpected.size()
        assert prefixNames     == termPathExpected

        where:
        nodeNameInput | termPathExpectedInput
        "cbc:StartDate" | ["ubl:Invoice", "cac:InvoicePeriod", "cbc:StartDate"]
    }
}
