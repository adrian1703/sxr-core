package sxr.core.utils.reflection

import sxr.model.entities.SxrObject
import sxr.model.interfaces.XmlElement

import java.lang.reflect.Constructor
import java.lang.reflect.Field

class SxrObjectUtil {

    static List<Field> GetXmlElementFields(Class clazz) {
        return clazz.declaredFields
                .findAll { Field field ->
                    XmlElement annotation = field.getAnnotation(XmlElement)
                    return annotation != null
                }
                .sort { Field field ->
                    field.getAnnotation(XmlElement).order()
                }.toList()
    }

    static Field FindXmlElementByTerm(Class clazz, String term) {
        return clazz.declaredFields
                    .find { Field field ->
                        XmlElement annotation = field.getAnnotation(XmlElement)
                        return annotation != null && annotation.term() == term
                    }
    }

    static SxrObject constructSxrFromField(Field field) {
        XmlElement annotation      = field.getAnnotation(XmlElement)
        Constructor constructor    = annotation.type().getConstructor()
        return constructor.newInstance() as SxrObject
    }
}
