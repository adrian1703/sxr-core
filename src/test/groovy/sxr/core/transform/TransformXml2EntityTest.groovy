package sxr.core.transform

import spock.lang.Specification
import sxr.core.transform.builders.invoice.InvoiceXml2EntityBuilder
import sxr.core.transform.directors.DefaultTransformDirector
import sxr.core.transform.readers.XmlReader
import sxr.model.entities.SxrObject
import sxr.model.entities.invoice.Invoice
import test.ResourcesUtil

class TransformXml2EntityTest extends Specification{
    Reader<String, Node> reader
    TransformBuilder<Node, SxrObject> builder
    TransformDirector transformDirector

    def setup() {
        reader = new XmlReader()
        builder = new InvoiceXml2EntityBuilder()
        transformDirector = new DefaultTransformDirector()
    }

    def "XmlStringToInvoiceEntity"() {
        given:
        List<File> files = ResourcesUtil.getFilesFor(ResourcesUtil.UBLTestcase.BUSINESS_CASES_STANDARD)
        String xml = files[0].text

        when:
        SxrObject construct = transformDirector.construct(xml, reader, builder)
        then:
        assert construct.class == Invoice.class
        assert (construct as Invoice).seller.party.sellerElectronicAddress_schemeID == "EM"
    }
}
