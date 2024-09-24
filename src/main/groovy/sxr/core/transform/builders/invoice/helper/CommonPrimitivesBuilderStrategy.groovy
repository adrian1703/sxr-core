package sxr.core.transform.builders.invoice.helper

import sxr.core.utils.Composite

import java.lang.reflect.Field
import java.sql.Date

class CommonPrimitivesBuilderStrategy implements BuilderStrategy{
    static List<BuilderStrategy> substrategies = [
            new TypeBasedStrategy(String.class,              {String it -> it })
            , new TypeBasedStrategy(Boolean.class,           {String it -> Boolean.parseBoolean it })
            , new TypeBasedStrategy(Float.class,             {String it -> Float.parseFloat it })
            , new TypeBasedStrategy(Integer.class,           {String it -> Integer.parseInt it })
            , new TypeBasedStrategy(java.util.Date.class,    {String it -> Date.valueOf it })

    ]
    @Override
    boolean consumes(Field field) {
        return substrategies.find { it.consumes(field) } != null
    }

    @Override
    Composite<SxrAndNode> handleBuild(Field field,
                                      Node node,
                                      final Composite<SxrAndNode> treeRoot,
                                      final Composite<SxrAndNode> parentPointer) {
        BuilderStrategy strategy = substrategies.find { it.consumes(field) }
        return strategy.handleBuild(field, node, treeRoot, parentPointer)
    }
}
