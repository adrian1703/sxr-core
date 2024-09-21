package sxr.core.utils.reflection

import sxr.model.interfaces.XmlElement

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
}
