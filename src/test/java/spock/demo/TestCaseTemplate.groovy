package spock.demo

import spock.lang.Ignore
import spock.lang.IgnoreRest
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Subject
import spock.lang.Timeout
import spock.lang.Title

import java.util.concurrent.TimeUnit


@Title("测试的标题")
@Narrative("""关于测试的大段文本描述""")
@Subject(Calculator)  //标明被测试的类是Calculator
@Stepwise  //当测试方法间存在依赖关系时，标明测试方法将严格按照其在源代码中声明的顺序执行
class TestCaseTemplate extends Specification {
    @Shared
    //在测试方法之间共享的数据
    CacheService cacheService

    def setupSpec() {
        //TODO: 设置每个测试类的环境
    }

    def setup() {
        //TODO: 设置每个测试方法的环境，每个测试方法执行一次
    }

    @Ignore("忽略这个测试方法")
    @Issue(["问题#23", "问题#34"])
    def "测试方法1"() {
        given: "给定一个前置条件"
        //TODO: code here
        and: "其他前置条件"


        expect: "随处可用的断言"
        //TODO: code here
        when: "当发生一个特定的事件"
        //TODO: code here
        and: "其他的触发条件"

        then: "产生的后置结果"
        //TODO: code here
        and: "同时产生的其他结果"

        where: "不是必需的测试数据"
        input1 | input2 || output
//        ... | ... || ...
    }

    @IgnoreRest
    //只测试这个方法，而忽略所有其他方法
    @Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
    // 设置测试方法的超时时间，默认单位为秒
    def "测试方法2"() {
        //TODO: code here
    }

    def cleanup() {
        //TODO: 清理每个测试方法的环境，每个测试方法执行一次
    }

    def cleanupSepc() {
        //TODO: 清理每个测试类的环境
    }
}