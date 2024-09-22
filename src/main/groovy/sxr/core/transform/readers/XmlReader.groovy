package sxr.core.transform.readers

import sxr.core.utils.XmlUtil

class XmlReader extends AbstractReader<String, Node> {

    @Override
    Iterator<Node> makeIterator(String srcObject) {
        Node        xmlRoot = XmlUtil.Instance().parseTextToNode(srcObject)
        List<Node> allNodes = xmlRoot.'**'.findAll{it instanceof Node}
        return allNodes.iterator()
    }
}
