package sxr.core

import sxr.model.entities.Invoice

class XmlInvoiceFactory implements InvoiceFactory{
    @Override
    Invoice createInvoice(Object input) {
        return new Invoice()
    }
}
