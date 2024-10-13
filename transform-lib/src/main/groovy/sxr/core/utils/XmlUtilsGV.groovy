package sxr.core.utils

import groovy.namespace.QName
import groovy.xml.XmlParser
import groovy.xml.XmlUtil

class XmlUtilsGV implements XmlUtils {
    static XmlUtilsGV Singleton
           XmlParser  xmlParser = new XmlParser()

    static XmlUtilsGV Instance() {
        if (Singleton == null)
            Singleton = new XmlUtilsGV()
        return Singleton
    }

    Node parseFileToNode(String path) {
        def file = new File(path)
        return Instance().xmlParser.parse(file)
    }

    Node parseTextToNode(String text) {
        return Instance().xmlParser.parseText(text)
    }

    String parseNodeToText(Node node) {
        return XmlUtil.serialize(node)
    }

    List<QName> getXmlTermPath(Node node) {
        List termPath = [node.name()]
        Node current  = node
        while (current.parent() != null) {
            current = current.parent()
            termPath << current.name()
        }
        return termPath.reversed() as List<QName>
    }
}
