package sxr.core.readers.directors

import sxr.core.readers.builders.AbstractTransformBuilder

/**
 * Director class (Builder pattern) for doing an object transformation
 * @param <A> Type of the source-object the transformation is based on for the reader
 * @param <B> Type of input that is delegated to the builder
 */
interface AbstractReader<A,B> extends Iterable<B>{

    /**
     * Sets the Object that is the input for conversion
     * @param srcObject
     * @return this
     */
    AbstractReader setSrcObject(A srcObject)

    /**
     * This uses a TransformBuilder<B,C> to construct a new object converting A->C using B inputs
     * @return the constructed object by TransformBuilder
     */
    Object construct(AbstractTransformBuilder<B,?> builder)

}