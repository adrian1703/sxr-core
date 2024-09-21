package sxr.core.utils

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

    Node parseFileToNode(String path) {
        def file = new File(path)
        return xmlParser.parse(file)
    }

    Node parseTextToNode(String text) {
        return xmlParser.parseText(text)
    }

    static String parseNodeToText(Node node) {
        return groovy.xml.XmlUtil.serialize(node)
    }

    SxrObject parseNodeToSxrObject(Node node) {

    }
}
