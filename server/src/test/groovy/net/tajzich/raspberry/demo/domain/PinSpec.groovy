package net.tajzich.raspberry.demo.domain

import org.junit.Test
import spock.lang.Specification

class PinSpec extends Specification {

    Pin pin

    def setup() {
        pin = new Pin()
    }

    void 'should behave correctly'() {

        given:

        pin.inverted = inverted
        pin.voltage = voltage

        when:

        boolean result = pin.isHigh()

        then:

        high == result

        where:

        inverted | voltage | high
        false    | false   | false
        false    | true    | true
        true     | false   | true
        true     | true    | false
    }


    @Test
    void 'not inverted and low'() {

        given:

        Pin pin = new Pin(inverted: false)

        when:
        pin.low()

        then:
        !pin.isHigh()
        !pin.voltage
    }

    @Test
    void 'not inverted and high'() {

        given:

        Pin pin = new Pin(inverted: false)

        when:

        pin.high()

        then:

        pin.isHigh()
        pin.voltage
    }

    @Test
    void 'inverted and low'() {

        given:

        Pin pin = new Pin(inverted: true)

        when:

        pin.low()

        then:

        !pin.isHigh()
        pin.voltage
    }

    @Test
    void 'inverted and high'() {

        given:

        Pin pin = new Pin(inverted: true)

        when:

        pin.high()

        then:

        pin.isHigh()
        !pin.voltage
    }
}
