package sxr.core.readers.directors.invoice

import sxr.core.readers.directors.AbstractReader
import sxr.core.readers.directors.AbstractReaderImpl
import sxr.core.utils.XmlUtil

class InvoiceXmlReader extends AbstractReaderImpl<String, Node> {
    Node invoiceXmlRoot
    @Override
    AbstractReader setSrcObject(String srcObject) {
        invoiceXmlRoot = XmlUtil.Instance().parseTextToNode(srcObject)
        return this
    }

    @Override
    Iterator<Node> iterator() {
        List<Node> allNodes = invoiceXmlRoot.'**'.findAll{it instanceof Node}
        return allNodes.iterator()
    }
}
