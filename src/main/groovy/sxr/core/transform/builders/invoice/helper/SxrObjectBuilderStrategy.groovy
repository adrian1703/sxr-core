package sxr.core.transform.builders.invoice.helper

import sxr.core.utils.Composite
import sxr.core.utils.reflection.SxrObjectUtil
import sxr.model.entities.SxrObject

import java.lang.reflect.Field

class SxrObjectBuilderStrategy implements BuilderStrategy {
    @Override
    boolean consumes(Field field) {
        return SxrObject.class.isAssignableFrom(field.getType())
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field,
                                      Node node,
                                      final Composite<SxrAndNode> treeRoot,
                                      final Composite<SxrAndNode> parentPointer) {
        SxrObject child            = SxrObjectUtil.constructSxrFromField(field)
        Composite<SxrAndNode> comp = parentPointer.addChild(new SxrAndNode(child, node))
        field.set(parentPointer.value.sxr, child)
        return comp
    }
}
