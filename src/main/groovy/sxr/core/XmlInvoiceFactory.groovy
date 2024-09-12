package sxr.core

import sxr.model.entities.Invoice

class XmlInvoiceFactory implements InvoiceFactory<String>{
    @Override
    Invoice deserializeInvoice(String input) {
        return new Invoice()
    }

    @Override
    String serializeInvoice(Invoice input) {
        return "null"
    }
}
