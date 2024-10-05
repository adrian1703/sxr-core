package sxr.core.transform.builders.invoice.helper

import sxr.core.model.interfaces.XmlAttribute
import sxr.core.model.interfaces.XmlElement
import sxr.core.utils.Composite
import sxr.core.utils.reflection.SxrObjectUtil

import java.lang.reflect.Field

class EnumBuilderStrategy implements BuilderStrategy {
    @Override
    boolean consumes(Field field) {
        return (field.getAnnotation(XmlElement) != null && field.type.name.startsWith("sxr.model.codes."))
                ||
               (field.getAnnotation(XmlAttribute) != null && field.type.name.startsWith("sxr.model.codes."))
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field,
                                      Node node,
                                      final Composite<SxrAndNode> treeRoot,
                                      final Composite<SxrAndNode> parentPointer) {
        Class<?> enumClass
        String enumCode
        if(field.getAnnotation(XmlElement) != null) {
            enumClass = field.getAnnotation(XmlElement).type()
            enumCode  = node.text()
        } else {
            enumClass = field.getAnnotation(XmlAttribute).type()
            enumCode  = node.attribute(field.getAnnotation(XmlAttribute).term())
        }
        def constant = SxrObjectUtil.getEnumByCode(enumClass, enumCode)
        field.set(parentPointer.value.sxr, constant)
        return null
    }
}
