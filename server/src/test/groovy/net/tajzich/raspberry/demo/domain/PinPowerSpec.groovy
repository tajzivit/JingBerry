package net.tajzich.raspberry.demo.domain

import spock.lang.Specification

class PinPowerSpec extends Specification {

    def setup() {

        //128 64 32 16 8 4 2 1
    }

    void 'should properly calculate pin power'() {

        when:

        int result = PinPower.getPinPower(original)

        then:

        result == power

        where:

        original | power
        0        | 1
        1        | 2
        2        | 4
        3        | 8
        4        | 16
        5        | 32
        6        | 64
        7        | 128
    }

    void 'no power should be zero'() {

        given:

        def bits = createByte()

        when:

        byte result = PinPower.getValueInByte(bits)

        byte expected = 0

        then:

        expected == result
    }

    void 'first bit should be one'() {

        given:

        def bits = createByte()

        bits.first().set = true

        when:

        byte result = PinPower.getValueInByte(bits)

        byte expected = 1

        then:

        expected == result
    }

    void 'should set correct bit'() {

        given:

        def bits = createByte()

        when:

        bits[index].set = true

        then:

        ((byte) expectedByte) == PinPower.getValueInByte(bits)

        where:

        index | expectedByte
        1     | 2
        2     | 4
        3     | 8
        4     | 16
        5     | 32
        6     | 64

    }

    void 'last bit should have value 128'() {

        given:

        def bits = createByte()

        when:

        bits.last().set = true

        byte expected = 128

        then:

        expected == PinPower.getValueInByte(bits)
    }

    void 'calculate sum'() {

        given:

        def bits = createByte()

        when:

        indexes.each {
            bits[it].set = true
        }

        byte result = PinPower.getValueInByte(bits)

        then:

        ((byte) expected) == result

        where:

        indexes                  | expected
        [0, 1]                   | 3
        [0, 1, 2]                | 7
        [0, 1, 2, 3]             | 15
        [0, 1, 2, 3, 4]          | 31
        [0, 1, 2, 3, 4, 5]       | 63
        [0, 1, 2, 3, 4, 5, 6]    | 127
        [0, 1, 2, 3, 4, 5, 6, 7] | 255
    }

    private List<Bit> createByte() {

        List<Bit> bits = []

        8.times {
            bits << new Bit(it, false)
        }

        return bits
    }
}
