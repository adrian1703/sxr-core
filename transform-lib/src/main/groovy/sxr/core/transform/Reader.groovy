package sxr.core.transform
/**
 * Director class (Builder pattern) for doing an object transformation
 * @param <A> Type of the source-object the transformation is based on for the reader
 * @param <B> Type of input that is delegated to the builder
 */
interface Reader<A,B> {

    /**
     * Constructs an iterator, reading the
     * srcObject
     * @param srcObject
     * @return Iterator resolved from srcObject
     */
    Iterator<B> makeIterator(A srcObject)

    /**
     * If a Src-Object has different representations,
     * the reader can implement this method to
     * do an alternative object conversion in order
     * to construct an Iterator.
     * @param srcObject
     * @return Iterator resolved from srcObject
     * @throws UnsupportedOperationException if not implemented by reader
     */
    Iterator<B> tryMakeIterator(Object srcObject) throws UnsupportedOperationException
}