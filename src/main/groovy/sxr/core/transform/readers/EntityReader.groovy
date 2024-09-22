package sxr.core.transform.readers


import sxr.core.utils.reflection.SxrObjectUtil
import sxr.model.entities.SxrObject

import java.lang.reflect.Field

class EntityReader extends AbstractReader<SxrObject, SxrObject> {

    @Override
    Iterator<SxrObject> makeIterator(SxrObject srcObject) {
        SxrObject       root  = srcObject
        List<SxrObject> parts = createParts(root, [])
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
