package sxr.core;

import sxr.model.entities.Invoice;

public interface InvoiceFactory {

    Invoice createInvoice(Object input);
}
