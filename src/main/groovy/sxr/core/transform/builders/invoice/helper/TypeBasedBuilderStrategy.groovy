package sxr.core.transform.builders.invoice.helper

import sxr.core.utils.Composite
import sxr.model.interfaces.XmlElement

import java.lang.reflect.Field

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
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field, Node node, Composite<SxrAndNode> treeRoot, Composite<SxrAndNode> parentPointer) {
        def value = parseValue(node.text())
        field.set(parentPointer.value.sxr, value)
        return null
    }
}