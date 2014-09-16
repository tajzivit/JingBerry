package net.tajzich.raspberry.demo.domain

import org.junit.Test
import spock.lang.Specification

class AbstractPeripherySpec extends Specification {

    AbstractPeriphery periphery

    def setup() {

        periphery = new TestPeriphery()
        periphery.setupHWLayout()
    }

    void 'should write zero'() {

        when:

        byte writtenData = periphery.write()

        then:
        0 == writtenData
    }

    void 'should write 1 for first pin'() {

        when:

        periphery.pins.first().high()

        byte writtenData = periphery.write()

        then:

        1 == writtenData
    }

    void 'written data should be correct'() {

        when:

        periphery.pins[index].high()

        byte writtenData = periphery.write()

        then:

        ((byte) expected) == writtenData

        where:

        index | expected
        1     | 2
        2     | 4
        3     | 8
        4     | 16
        5     | 32
        6     | 64
    }

    void 'last pin should write 128'() {

        when:

        periphery.pins.last().high()

        byte writtenData = periphery.write()

        byte expected = 128

        then:

        expected == writtenData
    }

    void 'all pins shouled write 255'() {

        when:

        periphery.pins.each {
            it.high()
        }

        byte writtenData = periphery.write()

        byte expected = 255

        then:

        expected == writtenData
    }

    void 'pin 0 is output'() {

        given:

        List<Pin> pinsToBeSet = []

        pinsToBeSet << new Pin(number: 0, address: PinPower.getPinPower(0), voltage: true)

        when:

        byte result = periphery.setOutput(pinsToBeSet)

        byte expected = 1

        then:

        expected == result
    }

    void 'all pins as output'() {

        given:

        List<Pin> pinsToBeSet = []

        8.times {
            pinsToBeSet << new Pin(number: it, address: PinPower.getPinPower(it), voltage: true)
        }

        when:

        byte result = periphery.setOutput(pinsToBeSet)

        byte expected = 255

        then:

        expected == result
    }

    void 'all output and inverted and high'() {

        given:

        periphery.pins.each {

            it.inverted = true
            it.high()
        }

        when:

        byte result = periphery.write()

        byte expected = 0

        then:

        expected == result
    }

    @Test
    void 'all output and inverted and low'() {

        given:

        periphery.pins.each {

            it.inverted = true
            it.low()
        }

        when:

        byte result = periphery.write()

        byte expected = 255

        then:

        expected == result
    }

    static class TestPeriphery extends AbstractPeriphery {

        @Override
        void writeToHW(byte data) {
            println "written to HW: ${data & 0xff}"
        }

        @Override
        void setupHWLayout() {
            8.times {
                Pin pin = new Pin(name: "P$it", number: it, inverted: false, address: PinPower.getPinPower(it))
                pin.low()
                pins << pin
            }
        }
    }
}
