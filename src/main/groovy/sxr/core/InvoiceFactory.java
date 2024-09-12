package sxr.core;

import sxr.model.entities.Invoice;

public interface InvoiceFactory<T> {

    Invoice deserializeInvoice(T input);

    T serializeInvoice(Invoice input);
}
