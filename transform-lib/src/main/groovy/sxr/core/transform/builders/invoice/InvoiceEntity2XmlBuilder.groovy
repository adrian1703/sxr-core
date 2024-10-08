package sxr.core.transform.builders.invoice

import sxr.core.model.entities.SxrObject
import sxr.core.transform.TransformBuilder

class InvoiceEntity2XmlBuilder implements TransformBuilder<SxrObject, Map>{
    Map invoice

    @Override
    InvoiceEntity2XmlBuilder init() {
        invoice = [:]
        return this
    }

    @Override
    InvoiceEntity2XmlBuilder add(SxrObject toAdd) {
        return this
    }

    @Override
    Map getResult() {
        return invoice
    }
}
