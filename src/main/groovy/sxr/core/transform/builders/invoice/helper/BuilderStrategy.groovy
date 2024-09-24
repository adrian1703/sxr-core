package sxr.core.transform.builders.invoice.helper

import sxr.core.utils.Composite

import java.lang.reflect.Field

/**
 * Strategy for providing different algorithms
 * depending on the type of field provided.
 */
interface BuilderStrategy {

    /**
     * Returns to if this strategy is supposed to handle
     * the field.
     * @param field to check
     * @return
     */
    boolean consumes(Field field)

    /**
     * This handles the assignment of
     * a field to a given node-value.
     * @param field of SxrObject with XmlElementAnnotation
     * @param node corresponding node obj of xml
     * @param treeRoot additional information - DO NOT MODIFY
     * @param parentPointer additional information - DO NOT MODIFY
     * @return if an SxrObject is created return the composite to be added to the tree structure
     */
    Composite<SxrAndNode> handleBuild(Field field,
                                      Node node,
                                      final Composite<SxrAndNode> treeRoot,
                                      final Composite<SxrAndNode> parentPointer)
}