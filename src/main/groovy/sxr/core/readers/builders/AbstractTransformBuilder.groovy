package sxr.core.readers.builders

/**
 * A builder class that iteratively constructs an object
 * from a series of inputs.
 * @param <A> Type of input this builder accepts
 * @param <B> Type of the constructed object
 */
interface AbstractTransformBuilder<B, C> {

    AbstractTransformBuilder<B,C> init()
    AbstractTransformBuilder<B,C> add(B toAdd)
    C getResult()
}
