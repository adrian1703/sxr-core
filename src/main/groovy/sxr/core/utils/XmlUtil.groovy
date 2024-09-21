package sxr.core.utils

import groovy.namespace.QName
import groovy.xml.XmlParser
import sxr.model.entities.SxrObject

class XmlUtil {
    static XmlUtil   Singleton
           XmlParser xmlParser = new XmlParser()

    static XmlUtil Instance() {
        if (Singleton == null)
            Singleton = new XmlUtil()
        return Singleton
    }

    static Node parseFileToNode(String path) {
        def file = new File(path)
        return Instance().xmlParser.parse(file)
    }

    static Node parseTextToNode(String text) {
        return Instance().xmlParser.parseText(text)
    }

    static String parseNodeToText(Node node) {
        return groovy.xml.XmlUtil.serialize(node)
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

    SxrObject parseNodeToSxrObject(Node node) {

    }

}
