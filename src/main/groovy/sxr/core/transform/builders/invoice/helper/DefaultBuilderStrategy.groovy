package sxr.core.transform.builders.invoice.helper

import groovy.util.logging.Log
import sxr.core.utils.Composite
import sxr.model.interfaces.XmlElement

import java.lang.reflect.Field

@Log
class DefaultBuilderStrategy implements BuilderStrategy {

    @Override
    boolean consumes(Field field) {
        return false
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field, Node node, Composite<SxrAndNode> treeRoot, Composite<SxrAndNode> parentPointer) {
        log.info "Unhandled field type ${field.getAnnotation(XmlElement).type()}"
        return null
    }
}
