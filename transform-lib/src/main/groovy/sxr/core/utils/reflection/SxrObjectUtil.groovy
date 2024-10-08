package sxr.core.utils.reflection

import sxr.core.model.entities.SxrObject
import sxr.core.model.interfaces.XmlAttribute
import sxr.core.model.interfaces.XmlElement

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

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

    static List<Field> GetAttributeFieldsOfXmlElement(Class clazz, Field parentField) {
        String fieldName = parentField.name
        return clazz.declaredFields
                    .findAll { Field field ->
                        XmlAttribute annotation = field.getAnnotation(XmlAttribute)
                        return annotation != null && annotation.parent() == fieldName
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

    static <T extends Enum<?>> T getEnumByCode(Class<T> enumClass, String code) {
        try {
            T[] constants = enumClass.getEnumConstants();
            Method method = enumClass.getMethod("getCode");
            for (T constant : constants) {
                String constantCode = (String) method.invoke(constant);
                if (constantCode.equals(code)) {
                    return constant;
                }
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
