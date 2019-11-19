## Usage


run all tests

        gradle test 

run current class or method

    gradle test --tests LoginTest
    gradle test --tests DescTest.addList

run current suite

    gradle clean test -PsuiteName=<name of xml suite file>
    gradle clean test -PsuiteName=simpleSuite