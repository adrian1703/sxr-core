package sxr.core.transform.readers

import sxr.core.transform.Reader

abstract class ReaderImpl<A,B> implements Reader<A,B> {

    Iterator<B> tryMakeIterator(Object srcObject)
    throws UnsupportedOperationException {
        try{
            return makeIterator(srcObject as A)
        } catch (ignored) {}
        throw new UnsupportedOperationException("Not implemented")
    }
}