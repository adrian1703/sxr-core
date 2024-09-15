package sxr.core

import groovy.namespace.QName
import sxr.model.entities.invoice.Invoice


class XmlInvoiceFactory implements InvoiceFactory<String>{

    XmlUtil xmlUtil = new XmlUtil()


    @Override
    Invoice deserializeInvoice(String input) {
        Node root       = xmlUtil.parseTextToNode(input)
        Invoice invoice = new Invoice()
        return new Invoice()
    }

    @Override
    String serializeInvoice(Invoice input) {
        return "null"
    }

    Invoice update(Invoice invoice, Node current) {
        return invoice
    }

    static List<QName> getXmlTermPath(Node node) {
        List termPath = [node.name()]
        Node current  = node
        while (current.parent() != null) {
            current = current.parent()
            termPath << current.name()
        }
        return termPath.reversed() as List<QName>
    }
}
