package sxr.core.transform

import sxr.core.transform.builders.AbstractTransformBuilder
import sxr.core.transform.readers.AbstractReader
//TODO: unfinished -> Handler Pattern / Chain of command delegation
class TransformDirectorImpl {
    Map<Class, List<AbstractReader>>           readerRegistry  = [:]
    Map<Class, List<AbstractTransformBuilder>> builderRegistry = [:]


    transform(Class a, Class c){
        lookupRegistry()
    }

    List<AbstractReader> lookUpReaderRegistry(Class source) {
        return readerRegistry.get(source, [])
    }

    List<AbstractTransformBuilder> lookUpTransformBuilderRegistry(Class target) {
        return builderRegistry.get(target, [])
    }
}
