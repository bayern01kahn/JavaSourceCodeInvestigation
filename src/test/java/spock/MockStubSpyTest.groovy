import org.spockframework.mock.TooFewInvocationsError
import org.spockframework.runtime.InvalidSpecException
import spock.lang.FailsWith
import spock.lang.Specification

class MockStubSpyTest extends Specification {

    static class Publisher {
        List<Subscriber> subscribers = new ArrayList<>()

        void addSubscriber(Subscriber subscriber) {
            subscribers.add(subscriber)
        }

        void send(String message) {
            for (Subscriber subscriber : subscribers)
                subscriber.receive(message);
        }
    }

    static interface Subscriber {
        String receive(String message)
    }

    static class MySubscriber implements Subscriber {
        @Override
        String receive(String message) {
            if (message ==~ /[A-Za-z ]+/)
                return "ok"
            return "uh-oh"
        }
    }

    Subscriber realSubscriber1 = new MySubscriber()
    Subscriber realSubscriber2 = new MySubscriber()
    Publisher publisher = new Publisher(subscribers: [realSubscriber1, realSubscriber2])

    def "Real objects can be tested normally"() {
        expect:
        realSubscriber1.receive("Hello subscribers") == "ok"
        realSubscriber1.receive("Anyone there?") == "uh-oh"
    }

    @FailsWith(TooFewInvocationsError)
    def "Real objects cannot have interactions"() {
        when:
        publisher.send("Hello subscribers")
        publisher.send("Anyone there?")

        then:
        2 * realSubscriber1.receive(_)
    }

    def "Stubs can simulate behaviour"() {
        given:
        def stubSubscriber = Stub(Subscriber) {
            receive(_) >>> ["hey", "ho"]
        }

        expect:
        stubSubscriber.receive("Hello subscribers") == "hey"
        stubSubscriber.receive("Anyone there?") == "ho"
        stubSubscriber.receive("What else?") == "ho"
    }

    @FailsWith(InvalidSpecException)
    def "Stubs cannot have interactions"() {
        given: "stubbed subscriber registered with publisher"
        def stubSubscriber = Stub(Subscriber) {
            receive(_) >> "hey"
        }
        publisher.addSubscriber(stubSubscriber)

        when:
        publisher.send("Hello subscribers")
        publisher.send("Anyone there?")

        then:
        2 * stubSubscriber.receive(_)
    }

    def "Mocks can simulate behaviour and have interactions"() {
        given:
        def mockSubscriber = Mock(Subscriber) {
            3 * receive(_) >>> ["hey", "ho"]
        }
        publisher.addSubscriber(mockSubscriber)

        when:
        publisher.send("Hello subscribers")
        publisher.send("Anyone there?")

        then: "check interactions"
        1 * mockSubscriber.receive("Hello subscribers")
        1 * mockSubscriber.receive("Anyone there?")

        and: "check behaviour exactly 3 times"
        mockSubscriber.receive("foo") == "hey"
        mockSubscriber.receive("bar") == "ho"
        mockSubscriber.receive("zot") == "ho"
    }

    def "Spies can have interactions"() {
        given:
        def spySubscriber = Spy(MySubscriber)
        publisher.addSubscriber(spySubscriber)

        when:
        publisher.send("Hello subscribers")
        publisher.send("Anyone there?")

        then: "check interactions"
        1 * spySubscriber.receive("Hello subscribers")
        1 * spySubscriber.receive("Anyone there?")

        and: "check behaviour for real object (a spy is not a mock!)"
        spySubscriber.receive("Hello subscribers") == "ok"
        spySubscriber.receive("Anyone there?") == "uh-oh"
    }

    def "Spies can modify behaviour and have interactions"() {
        given:
        def spyPublisher = Spy(Publisher) {
            send(_) >> { String message -> callRealMethodWithArgs("#" + message) }
        }
        def mockSubscriber = Mock(MySubscriber)
        spyPublisher.addSubscriber(mockSubscriber)

        when:
        spyPublisher.send("Hello subscribers")
        spyPublisher.send("Anyone there?")

        then: "check interactions"
        1 * mockSubscriber.receive("#Hello subscribers")
        1 * mockSubscriber.receive("#Anyone there?")
    }
}