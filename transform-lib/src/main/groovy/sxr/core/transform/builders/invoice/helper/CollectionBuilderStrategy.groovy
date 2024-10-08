package sxr.core.transform.builders.invoice.helper

import sxr.core.model.entities.SxrObject
import sxr.core.model.interfaces.XmlElement
import sxr.core.utils.Composite
import sxr.core.utils.reflection.SxrObjectUtil

import java.lang.reflect.Field

class CollectionBuilderStrategy implements BuilderStrategy {
    @Override
    boolean consumes(Field field) {
        XmlElement annotation = field.getAnnotation(XmlElement)
        if (annotation == null)
            return false
        Class collectionType  = annotation.type()
        return List.class.isAssignableFrom(field.getType()) &&
               SxrObject.class.isAssignableFrom(collectionType)

    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field,
                                      Node node,
                                      final Composite<SxrAndNode> treeRoot,
                                      final Composite<SxrAndNode> parentPointer) {
        SxrObject child            = SxrObjectUtil.constructSxrFromField(field)
        Composite<SxrAndNode> comp = parentPointer.addChild(new SxrAndNode(child, node))
        List value = field.get(parentPointer.value.sxr) as List
        if(value == null) {
            value = []
            field.set(parentPointer.value.sxr, value)
        }
        value << child
        return comp
    }
}
