package sxr.core.transform
/**
 * The Director is used for managing the builder process in the build pattern.
 */
interface TransformDirector {
    /**
     * This uses a Reader A->B and a TransformBuilder B->C to construct a new
     * object converting A->C using B inputs
     * @return the constructed object by TransformBuilder
     */
    <A,B,C> C construct(A input, Reader<A,B> reader, TransformBuilder<B,C> builder)

    /**
     * This uses a Reader A->B and a TransformBuilder B->C to construct a new
     * object converting A->C using B inputs.
     * This uses the readers tryMakeIterator()
     * instead of makeIterator()
     * @return the constructed object by TransformBuilder
     */
    <A,B,C> C tryConstruct(Object input, Reader<A,B> reader, TransformBuilder<B,C> builder)
}