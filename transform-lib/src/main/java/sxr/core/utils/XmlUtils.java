package sxr.core.utils;

import groovy.namespace.QName;
import groovy.util.Node;

import java.util.List;

public interface XmlUtils {

    Node parseFileToNode(String path);
    Node parseTextToNode(String text);
    String parseNodeToText(Node node);
    List<QName> getXmlTermPath(Node node);
}
