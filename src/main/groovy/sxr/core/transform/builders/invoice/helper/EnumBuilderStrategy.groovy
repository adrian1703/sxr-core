package sxr.core.transform.builders.invoice.helper

import sxr.core.utils.Composite
import sxr.core.utils.reflection.SxrObjectUtil
import sxr.model.interfaces.XmlElement

import java.lang.reflect.Field

class EnumBuilderStrategy implements BuilderStrategy {
    @Override
    boolean consumes(Field field) {
        return field.getAnnotation(XmlElement) != null && field.type.name.startsWith("sxr.model.codes.")
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field,
                                      Node node,
                                      final Composite<SxrAndNode> treeRoot,
                                      final Composite<SxrAndNode> parentPointer) {
        Class<?> enumClass = field.getAnnotation(XmlElement).type()
        String enumCode    = node.text()
        def constant = SxrObjectUtil.getEnumByCode(enumClass, enumCode)
        field.set(parentPointer.value.sxr, constant)
        return null
    }
}
