package sxr.core.transform.readers

import sxr.core.transform.builders.AbstractTransformBuilder

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
    AbstractReader<A,B> setSrcObject(A srcObject)

    /**
     * If a Src-Object has different representations,
     * the reader can implement this method to
     * do an alternative object conversion.
     * @param srcObject
     * @return this
     * @throws UnsupportedOperationException if not implemented by reader
     */
    AbstractReader<A,B> trySetSrcObject(Object srcObject) throws UnsupportedOperationException

    /**
     * This uses a TransformBuilder<B,C> to construct a new object converting A->C using B inputs
     * @return the constructed object by TransformBuilder
     */
    Object construct(AbstractTransformBuilder<B,?> builder)

}