package sxr.core

import groovy.xml.XmlParser
import sxr.model.entities.SxrObject

class XmlUtil {

    XmlParser xmlParser = new XmlParser()

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
