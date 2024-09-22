package sxr.core.transform.readers.invoice

import groovy.namespace.QName
import spock.lang.Specification
import sxr.core.transform.readers.Reader
import sxr.core.transform.readers.XmlReader
import test.ResourcesUtil

import static test.ResourcesUtil.readTextFilesFromResources

class XmlReaderTest extends Specification {
    Reader<String,Node> reader
    ResourcesUtil resourcesUtil

    void setup() {
        reader        = new XmlReader()
        resourcesUtil = new ResourcesUtil()
    }

    def "SetInvoiceXmlRoot"() {
        given:
        String path = 'technical-cases/01.01_comprehensive_test_ubl.xml'
        String xml  = readTextFilesFromResources(path)
        when:
        Iterator<Node> iter = reader.makeIterator(xml)
        then:
        iter.toList().each {
            println "${(it.name() as QName).localPart} | ${it.text().take(20)}"
            assert it instanceof Node
        }
    }
}
