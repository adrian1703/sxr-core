package sxr.core.readers.directors


import sxr.core.utils.reflection.SxrObjectUtil
import sxr.model.entities.SxrObject

import java.lang.reflect.Field

class EntityReader extends AbstractReaderImpl<SxrObject, SxrObject> {
    SxrObject       root
    List<SxrObject> parts

    @Override
    AbstractReader<SxrObject, SxrObject> setSrcObject(SxrObject srcObject) {
        root  = srcObject
        parts = createParts(root, [])
        return this
    }

    @Override
    Iterator<SxrObject> iterator() {
        if(root == null)
            throw new IllegalArgumentException("The Src-Object has not been set yet.")
        return parts.iterator()
    }

    private List<SxrObject> createParts(SxrObject current, List<SxrObject> localParts) {
        localParts << current
        SxrObjectUtil.GetXmlElementFields(current.class).each { Field field ->
            def fieldValue = field.get(current)
            if (fieldValue == null)
                return
            if (fieldValue instanceof SxrObject) {
                createParts(fieldValue, localParts)
            } else if (fieldValue instanceof Collection) {
                (fieldValue as Collection).each { collectionEntry ->
                    if(collectionEntry instanceof SxrObject)
                        createParts(collectionEntry as SxrObject, localParts)
                }
            }
        }
        return localParts
    }
}
