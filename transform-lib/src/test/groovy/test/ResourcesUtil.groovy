package test

class ResourcesUtil {

    enum UBLTestcase {
        BUSINESS_CASES_STANDARD('business-cases/standard'),
        BUSINESS_CASES_EXTENSION('business-cases/extension'),
        TECHNICAL_CASES('technical-cases')

        final String value

        UBLTestcase(String value) {
            this.value = value
        }
    }

    static String readTextFilesFromResources(String path) {
        def resource = ResourcesUtil.class.getClassLoader().getResource(path)
        if (resource == null) {
            throw new FileNotFoundException("Resource not found!")
        }
        def fileContent = new File(resource.toURI()).text
        return fileContent
    }


    static List<File> getFilesFor(UBLTestcase testcase) {
        println testcase.value
        def resource = ResourcesUtil.class.getClassLoader().getResource(testcase.value)
        if (resource == null) {
            throw new FileNotFoundException("Resource not found!")
        }
        File dir = new File(resource.toURI())
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Path is not a directory")
        List files = dir.listFiles({File file, String name -> name.endsWith('ubl.xml')} as FilenameFilter)
        return files
    }
}
