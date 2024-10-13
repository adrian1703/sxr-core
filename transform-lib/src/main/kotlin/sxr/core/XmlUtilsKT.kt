package sxr.core

import groovy.namespace.QName
import groovy.util.Node
import groovy.xml.XmlParser
import sxr.core.utils.XmlUtils
import java.io.File

object XmlUtilsKT : XmlUtils {
    private val xmlParser: XmlParser by lazy { XmlParser() }

    override fun parseFileToNode(path:String): Node {
        val file: File = File(path)
        return xmlParser.parse(file)
    }

    override fun parseTextToNode(text: String?): Node {
        TODO("Not yet implemented")
    }

    override fun parseNodeToText(node: Node?): String {
        TODO("Not yet implemented")
    }

    override fun getXmlTermPath(node: Node?): MutableList<QName> {
        TODO("Not yet implemented")
    }
}