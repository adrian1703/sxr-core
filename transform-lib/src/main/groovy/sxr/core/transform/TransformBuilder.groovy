package sxr.core.transform

/**
 * A builder class that iteratively constructs an object
 * from a series of inputs.
 * @param <A> Type of input this builder accepts
 * @param <B> Type of the constructed object
 */
interface TransformBuilder<B, C> {

    TransformBuilder<B,C> init()
    TransformBuilder<B,C> add(B toAdd)
    C getResult()
}
