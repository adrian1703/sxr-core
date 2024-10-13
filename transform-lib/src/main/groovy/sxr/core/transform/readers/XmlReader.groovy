package sxr.core.transform.readers

import sxr.core.utils.XmlUtilsGV

class XmlReader extends AbstractReader<String, Node> {

    @Override
    Iterator<Node> makeIterator(String srcObject) {
        Node        xmlRoot = XmlUtilsGV.Instance().parseTextToNode(srcObject)
        List<Node> allNodes = xmlRoot.'**'.findAll{it instanceof Node}
        return allNodes.iterator()
    }
}
