package sxr.core.transform.builders.invoice.helper

import sxr.core.utils.Composite
import sxr.model.interfaces.XmlAttribute
import sxr.model.interfaces.XmlElement

import java.lang.reflect.Field

//TODO: XmlElement vs Attributes -> should be split i think
class TypeBasedBuilderStrategy implements BuilderStrategy {
    private Class<?>   targetType
    private Closure<?> parseValue

    TypeBasedBuilderStrategy(Class<?> targetType, Closure<?> parseValue) {
        this.targetType = targetType
        this.parseValue = parseValue
    }

    @Override
    boolean consumes(Field field) {
        return targetType == field.getAnnotation(XmlElement)?.type()
                || targetType == field.getAnnotation(XmlAttribute)?.type()
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field, Node node, Composite<SxrAndNode> treeRoot, Composite<SxrAndNode> parentPointer) {
        def value = isXmlElement(field)
                ? node.text()
                : node.attribute(field.getAnnotation(XmlAttribute).term())
        if(value == null)
            return null
        field.set(parentPointer.value.sxr, parseValue(value))
        return null
    }

    private static boolean isXmlElement(Field field) {
        return field.getAnnotation(XmlElement) != null
    }
}