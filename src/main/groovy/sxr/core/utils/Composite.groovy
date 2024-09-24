package sxr.core.utils

class Composite<T> {
    T                  value
    Composite<T>       parent
    List<Composite<T>> children = []

    Composite(T value) {
        this.value = value
    }

    Composite<T> findParent(Closure isTarget){
        return isTarget(this) ? this : parent?.findParent(isTarget)
    }

    Composite<T> findChild(Closure isTarget) {
        if (isTarget(this))
            return this
        for (Composite<T> child : children) {
            Composite<T> result = child.findChild(isTarget)
            if (result != null)
                return result
        }
        return null
    }

    Composite<T> addChild(T child) {
        Composite<T> childComposite = new Composite<T>(child)
        this.children << childComposite
        childComposite.parent = this
        return childComposite
    }

    Composite<T> addChild(Composite<T> child) {
        Composite<T> childComposite = child
        this.children << childComposite
        childComposite.parent = this
        return childComposite
    }
}
