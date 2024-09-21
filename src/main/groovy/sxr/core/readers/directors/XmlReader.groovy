package sxr.core.readers.directors


import sxr.core.utils.XmlUtil

class XmlReader extends AbstractReaderImpl<String, Node> {
    Node xmlRoot
    @Override
    AbstractReader setSrcObject(String srcObject) {
        xmlRoot = XmlUtil.Instance().parseTextToNode(srcObject)
        return this
    }

    @Override
    Iterator<Node> iterator() {
        List<Node> allNodes = xmlRoot.'**'.findAll{it instanceof Node}
        return allNodes.iterator()
    }
}
