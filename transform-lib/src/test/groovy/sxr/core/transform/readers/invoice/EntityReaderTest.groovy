package sxr.core.transform.readers.invoice

import sxr.invoice.model.entities.ContractReference
import sxr.invoice.model.entities.DeliverParty
import sxr.invoice.model.entities.DeliveryInformation
import sxr.invoice.model.entities.Invoice
import sxr.invoice.model.entities.InvoiceLine
import sxr.invoice.model.entities.InvoiceLinePeriod
import spock.lang.Specification
import sxr.core.model.entities.SxrObject
import sxr.core.transform.Reader
import sxr.core.transform.readers.EntityReader

class EntityReaderTest extends Specification {
    Reader<SxrObject, SxrObject> reader

    void setup() {
        reader = new EntityReader()
    }

    def "CreatePartsBasic"() {
        given:
        Invoice invoice        = new Invoice()
        ContractReference cr   = new ContractReference()
        DeliveryInformation di = new DeliveryInformation()
        DeliverParty dp        = new DeliverParty()

        cr.contractReference        = "whatever"

        invoice.contractReference   = cr
        invoice.deliveryInformation = di
        di.deliverParty             = dp

        when:
        Iterator<SxrObject> iterator = reader.makeIterator(invoice)

        then:
        Iterator expected = [invoice, cr, di, dp].iterator()
        while (iterator.hasNext() || expected.hasNext()) {
            assert iterator.next() == expected.next()
        }
    }

    def "CreatePartsCollection"() {
        given:
        Invoice invoice      = new Invoice()
        InvoiceLine iLine1   = new InvoiceLine()
        InvoiceLine iLine2   = new InvoiceLine()
        List iLines          = [iLine1, iLine2]

        invoice.invoiceLine       = iLines

        when:
        Iterator<SxrObject> iterator = reader.makeIterator(invoice)

        then:
        Iterator expected = [invoice, iLine1, iLine2].iterator()
        while (iterator.hasNext() || expected.hasNext()) {
            assert iterator.next() == expected.next()
        }
    }

    def "CreatePartsAll"() {
        given:
        Invoice invoice      = new Invoice()
        InvoiceLine iLine1   = new InvoiceLine()
        InvoiceLine iLine2   = new InvoiceLine()
        ContractReference cr = new ContractReference()
        InvoiceLinePeriod ip = new InvoiceLinePeriod()
        List iLines          = [iLine1, iLine2]

        cr.contractReference        = "whatever"
        invoice.buyerReference      = "sample Value"
        iLine1.invoicedQuantity     = 3
        iLine1.invoiceLinePeriod    = ip
        iLine2.invoiceLineNetAmount = 123.1

        invoice.contractReference = cr
        invoice.invoiceLine       = iLines

        when:
        Iterator<SxrObject> iterator = reader.makeIterator(invoice)

        then:
        Iterator expected = [invoice, cr, iLine1, ip, iLine2].iterator()
        assert iterator.size() == expected.size()
        while (iterator.hasNext() || expected.hasNext()) {
            assert iterator.next() == expected.next()
        }
    }
}
