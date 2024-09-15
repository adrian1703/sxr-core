package test

import spock.lang.Specification

import static test.ResourcesUtil.*

class ResourcesUtilTest extends Specification {

    void setup() {
    }

    void cleanup() {
    }

    def "ReadTextFilesFromResources"() {
        given:
        String path = 'technical-cases/01.01_comprehensive_test_ubl.xml'
        when:
        String result = readTextFilesFromResources(path)
        then:
        assert result
    }

    def "GetFilesFor"() {
        given:
        UBLTestcase testcase = inputTestcase as UBLTestcase
        when:
        List files = getFilesFor(testcase)
        then:
        assert files.size() >= (inputResult as Integer)

        where:
        inputTestcase | inputResult
        UBLTestcase.BUSINESS_CASES_STANDARD   | 25
        UBLTestcase.BUSINESS_CASES_EXTENSION  | 5
        UBLTestcase.TECHNICAL_CASES           | 4

    }
}
