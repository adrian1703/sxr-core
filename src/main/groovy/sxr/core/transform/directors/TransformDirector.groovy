package sxr.core.transform.directors

import sxr.core.transform.Reader
import sxr.core.transform.TransformBuilder

//TODO: unfinished -> Handler Pattern / Chain of command delegation
class TransformDirector extends AbstractDirector{
    Map<Class, List<Reader>> readerRegistry = [:]
    Map<Class, List<TransformBuilder>> builderRegistry = [:]


    def transform(Class a, Class c){
        lookupRegistry()
    }

    List<Reader> lookUpReaderRegistry(Class source) {
        return readerRegistry.get(source, [])
    }

    List<TransformBuilder> lookUpTransformBuilderRegistry(Class target) {
        return builderRegistry.get(target, [])
    }
}
