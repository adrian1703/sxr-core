package sxr.core.transform.builders.invoice

import groovy.namespace.QName
import groovy.util.logging.Log
import sxr.core.transform.TransformBuilder
import sxr.core.transform.builders.invoice.helper.*
import sxr.core.utils.Composite
import sxr.core.utils.invoice.InvoiceNamespace
import sxr.core.utils.reflection.SxrObjectUtil
import sxr.invoice.model.entities.Invoice

import java.lang.reflect.Field

@Log
class InvoiceXml2EntityBuilder implements TransformBuilder<Node, Invoice>{

    Invoice               invoice
    Composite<SxrAndNode> treeRoot
    Composite<SxrAndNode> currentPointer
    boolean               isInit
    List<BuilderStrategy> specificStrategies = [  new SxrObjectBuilderStrategy()
                                                , new CollectionBuilderStrategy()
                                                , new CommonPrimitivesBuilderStrategy()
                                                , new EnumBuilderStrategy()]
    BuilderStrategy       defaultStrategy    = new DefaultBuilderStrategy()

    @Override
    TransformBuilder<Node, Invoice> init() {
        isInit = false
        return this
    }
    /**
     * The transformation will do the following steps:
     * 1. locate the parent of toAdd in tree
     * 2. get the corresponding "parent" sxr object
     * 3. get the corresponding Class-Object-Field of toAdd
     * 4. process any attribute values of node
     * 5.1 field == nativ type
     * 5.1.1 assign the value of node to parent-sxr
     * 5.1.2 return
     * 5.2 field == SxrObject
     * 5.2.1 invoke constructor for specific class
     * 5.2.2 assign value
     * 5.2.3 goto 6.
     * 5.3 field == List of Sxr
     * 5.3.1 init emtpy List if value of said field is null
     * 5.3.2 append value to collection
     * 5.3.3 goto 6.
     * 6. append created SxrObject+Node to the proper parent in tree
     * 7. return
     * @param toAdd
     * @return this
     */
    //TODO: Handle Attributes
    @Override
    TransformBuilder<Node, Invoice> add(Node toAdd) {
        if (!isInit){
            customInit(toAdd)
            return this
        }

        Composite<SxrAndNode> parent, nextPointer
        Closure               isTarget
        Field                 targetField
        List<Field>           targetFieldAttributes
        String                targetFieldName
        BuilderStrategy       builderStrategy
        isTarget = { Composite<SxrAndNode> current -> current.value.node == toAdd.parent() }
        parent   = currentPointer.findParent(isTarget)
        if (parent == null)
            parent = treeRoot.findChild(isTarget)
        if (parent == null)
            throw new IllegalArgumentException("The provided Node $toAdd cannot be added to the build, because its parent has not yet been added.")
        targetFieldName = InvoiceNamespace.QNameToPrefixAndName(toAdd.name() as QName)
        targetField     = SxrObjectUtil.FindXmlElementByTerm(parent.value.sxr.class, targetFieldName)
        if (targetField == null){
            throw new IllegalArgumentException("TargetField is null: could not find: Parent: ${parent.value.sxr.class} Field: ${toAdd.name()}")
        }
        builderStrategy = specificStrategies.find { it.consumes(targetField) } ?: defaultStrategy
        nextPointer     = builderStrategy.handleBuild(targetField,
                                                      toAdd,
                                                      treeRoot,
                                                      parent)
        targetFieldAttributes = SxrObjectUtil.GetAttributeFieldsOfXmlElement(parent.value.sxr.class, targetField)
        targetFieldAttributes.each { Field attribute ->
            BuilderStrategy attribStrategy = specificStrategies.find { it.consumes(attribute) } ?: defaultStrategy
            attribStrategy.handleBuild(attribute,
                                       toAdd,
                                       treeRoot,
                                       parent)
        }
        currentPointer  = nextPointer ?: currentPointer
        return this
    }

    @Override
    Invoice getResult() {
        return invoice
    }

    private void customInit(Node rootNode) {
        invoice        = new Invoice()
        treeRoot       = new Composite<SxrAndNode>(new SxrAndNode(invoice, rootNode))
        currentPointer = treeRoot
        isInit         = true
    }
}
