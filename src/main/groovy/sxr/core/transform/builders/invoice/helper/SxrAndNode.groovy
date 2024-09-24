package sxr.core.transform.builders.invoice.helper

import groovy.transform.TupleConstructor
import sxr.model.entities.SxrObject

@TupleConstructor
class SxrAndNode {
    SxrObject sxr
    Node      node
}