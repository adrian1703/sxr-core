package sxr.core.utils.reflection

import spock.lang.Specification
import sxr.model.codes.Iso3166

class SxrObjectUtilTest extends Specification {

    def "GetEnumByCode"() {
        given:
        Class<?> clazz = Iso3166.class
        when:
        def result =  SxrObjectUtil.getEnumByCode(clazz, "DE")
        then:
        assert result == Iso3166.P_DE
    }
}
