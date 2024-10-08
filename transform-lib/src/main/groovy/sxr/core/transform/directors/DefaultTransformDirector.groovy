package sxr.core.transform.directors

import sxr.core.transform.Reader
import sxr.core.transform.TransformBuilder
import sxr.core.transform.TransformDirector

//TODO: unfinished -> Handler Pattern / Chain of command delegation maybe a handler class for that
class DefaultTransformDirector implements TransformDirector {

    Map<Class, List<Reader>>           readerRegistry  = [:]
    Map<Class, List<TransformBuilder>> builderRegistry = [:]

    @Override
    <A, B, C> C construct(A input, Reader<A, B> reader, TransformBuilder<B, C> builder) {
        Iterator<B> iterator = reader.makeIterator(input)
        return doConstruct(iterator, builder)
    }

    @Override
    <A, B, C> C tryConstruct(Object input, Reader<A, B> reader, TransformBuilder<B, C> builder) {
        Iterator<B> iterator = reader.tryMakeIterator(input)
        return doConstruct(iterator, builder)
    }

    private static <B, C> C doConstruct(Iterator<B> iterator, TransformBuilder<B, C> builder) {
        builder.init()
        while (iterator.hasNext()) {
            builder.add iterator.next()
        }
        return builder.getResult()
    }

    def transform(Class a, Class c) {
        lookupRegistry()
    }

    List<Reader> lookUpReaderRegistry(Class source) {
        return readerRegistry.get(source, [])
    }

    List<TransformBuilder> lookUpTransformBuilderRegistry(Class target) {
        return builderRegistry.get(target, [])
    }
}
