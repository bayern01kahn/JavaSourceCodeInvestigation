package spock.demo

import spock.lang.Specification


class CalculatorSpec extends Specification {
    // mock对象
//    CacheService cacheService = Mock()
    def cacheService = Mock(CacheService)

    def calculator

    void setup() {
        calculator = new Calculator(cacheService)
    }


    def  "is username equal to logged in username"() {
        // stub 打桩 这里的getUserName() 并未实现， 这里直接模拟它被调用 并返回了 某个值
        cacheService.getUserName(*_) >> "Justin"

        when:
        def result = calculator.isLoggedInUser("Justin")

        then:
        result
    }
}