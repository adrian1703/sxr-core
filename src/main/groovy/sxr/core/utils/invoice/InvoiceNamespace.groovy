package sxr.core.utils.invoice

import groovy.namespace.QName
import groovy.xml.Namespace

class InvoiceNamespace {

    static Namespace ubl = new Namespace("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2", 'ubl')
    static Namespace cac = new Namespace("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2", 'cac')
    static Namespace cbc = new Namespace("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", 'cbc')

    static String getPrefix(String uri) {
        return switch (uri) {
            case ubl.uri -> ubl.prefix
            case cac.uri -> cac.prefix
            case cbc.uri -> cbc.prefix
            default -> throw new IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    static List<String> qNameToPrefixAndName(List<QName> input) {
        return input.collect(({ QName qName ->
            return "${qName.prefix}:${qName.localPart}"
        } as Closure<String>))
    }
}
