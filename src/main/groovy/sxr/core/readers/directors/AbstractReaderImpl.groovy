package sxr.core.readers.directors

import sxr.core.readers.builders.AbstractTransformBuilder

abstract class AbstractReaderImpl<A,B> implements AbstractReader<A,B>{

    Object construct(AbstractTransformBuilder<B,?> builder) {
        Iterator<B> iterator = iterator()
        builder.init()
        while(iterator.hasNext()) {
            builder.add iterator.next()
        }
        return builder.getResult()
    }

    AbstractReader<A,B> trySetSrcObject(Object srcObject)
    throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented")
    }
}