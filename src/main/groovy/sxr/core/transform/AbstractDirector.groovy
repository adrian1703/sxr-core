package sxr.core.transform

import sxr.core.transform.builders.TransformBuilder
import sxr.core.transform.readers.Reader

class AbstractDirector implements Director {
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

    private static <B, C> C doConstruct(Iterator<B> iterator, TransformBuilder<B, C> builder){
        builder.init()
        while(iterator.hasNext()) {
            builder.add iterator.next()
        }
        return builder.getResult()
    }
}
