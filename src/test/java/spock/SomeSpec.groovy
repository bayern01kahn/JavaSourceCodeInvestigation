package spock

import spock.lang.Specification


class SomeSpec extends Specification {

    class User { String userName }

    private validateUser(User user) {
        if (!user) throw new Exception('no user')
        if (!user.userName) throw new Exception('no userName')
    }

    def 'validate valid user'() {
        when:
        validateUser(user)

        then:
        noExceptionThrown()

        where:
        user << [ new User(userName: 'tester'),
                new User(userName: 'joe')]
    }

    def 'validate invalid user'() {
        when:
        validateUser(user)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        user                     || expectedException | expectedMessage
        new User(userName: null) || Exception         | 'no userName'
        new User(userName: '')   || Exception         | 'no userName'
        null                     || Exception         | 'no user'
    }



}