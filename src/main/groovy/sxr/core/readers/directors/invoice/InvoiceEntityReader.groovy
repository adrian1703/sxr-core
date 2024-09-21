package sxr.core.readers.directors.invoice

import sxr.core.readers.directors.AbstractReader
import sxr.core.readers.directors.AbstractReaderImpl
import sxr.core.utils.reflection.SxrObjectUtil
import sxr.model.entities.SxrObject
import sxr.model.entities.invoice.Invoice

import java.lang.reflect.Field

class InvoiceEntityReader extends AbstractReaderImpl<Invoice, SxrObject> {
    Invoice            invoice
    List<SxrObject>    parts

    @Override
    AbstractReader setSrcObject(Invoice srcObject) {
        invoice = srcObject
        parts   = createParts(invoice, [])
        return this
    }

    @Override
    Iterator<SxrObject> iterator() {
        if(invoice == null)
            throw new IllegalArgumentException("The Src-Object has not been set yet.")
        return parts.iterator()
    }

    private List<SxrObject> createParts(SxrObject current, List<SxrObject> localParts) {
        localParts << current
        SxrObjectUtil.GetXmlElementFields(current.class).each { Field field ->
            def fieldValue = field.get(current)
            if (fieldValue == null)
                return
            if (fieldValue instanceof SxrObject) {
                createParts(fieldValue, localParts)
            } else if (fieldValue instanceof Collection) {
                (fieldValue as Collection).each { collectionEntry ->
                    if(collectionEntry instanceof SxrObject)
                        createParts(collectionEntry as SxrObject, localParts)
                }
            }
        }
        return localParts
    }
}
