package net.tajzich.raspberry.demo.domain

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
class PinPower {

    private static final Logger LOG = LoggerFactory.getLogger(PinPower)

    static int getPinPower(int number) {

        int power = 1

        if (number == 0) {
            return power
        }

        return (1..number).inject(power, { allPower, current -> allPower * 2 }) as Integer
    }

    static int getValue(List<Bit> bits) {

        Integer result = bits.findAll { it.isSet() }.sum { Bit bit -> getPinPower(bit.number) } as Integer

        LOG.info("Value of bits: $bits is $result")

        return result ?: 0
    }

    static byte getValueInByte(List<Bit> bits) {

        if (bits.size() > 8) {
            throw new IllegalArgumentException("Cannot convert more than 8 bits into byte!")
        }

        return (byte) getValue(bits)
    }
}
