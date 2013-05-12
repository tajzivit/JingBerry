package net.tajzich.raspberry.demo.domain

import org.junit.Before
import org.junit.Test

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/11/13
 */
class AbstractPeripheryTest {

    AbstractPeriphery periphery

    @Before
    void setUp() {

        periphery = new TestPeriphery()
        periphery.setupHWLayout()
    }

    @Test
    void testWrite() {

        byte writtenData = periphery.write()
        assert 0 == writtenData
    }

    @Test
    void testWritePin0() {

        periphery.pins.first().high()

        byte writtenData = periphery.write()
        assert 1 == writtenData
    }

    @Test
    void testWritePin1() {

        periphery.pins[1].high()

        byte writtenData = periphery.write()
        assert 2 == writtenData
    }

    @Test
    void testWritePin3() {

        periphery.pins[2].high()

        byte writtenData = periphery.write()
        assert 4 == writtenData
    }

    @Test
    void testWritePin4() {

        periphery.pins[3].high()

        byte writtenData = periphery.write()
        assert 8 == writtenData
    }

    @Test
    void testWritePin5() {

        periphery.pins[4].high()

        byte writtenData = periphery.write()
        assert 16 == writtenData
    }

    @Test
    void testWritePin6() {

        periphery.pins[5].high()

        byte writtenData = periphery.write()
        assert 32 == writtenData
    }

    @Test
    void testWritePin7() {

        periphery.pins[6].high()

        byte writtenData = periphery.write()
        assert 64 == writtenData
    }

    @Test
    void testWritePin8() {

        periphery.pins.last().high()

        byte writtenData = periphery.write()

        byte expected = 128

        assert expected == writtenData
    }

    @Test
    void testWriteAll() {

        periphery.pins.each {
            it.high()
        }

        byte writtenData = periphery.write()

        byte expected = 255

        assert expected == writtenData
    }

    @Test
    void setOutputPin0() {

        List<Pin> pinsToBeSet = []

        pinsToBeSet << new Pin(number: 0, address: PinPower.getPinPower(0), voltage: true)

        byte result = periphery.setOutput(pinsToBeSet)

        byte expected = 1

        assert expected == result
    }

    @Test
    void setOutputAll() {

        List<Pin> pinsToBeSet = []

        8.times {
            pinsToBeSet << new Pin(number: it, address: PinPower.getPinPower(it), voltage: true)
        }

        byte result = periphery.setOutput(pinsToBeSet)

        byte expected = 255

        assert expected == result
    }

    @Test
    void setOutputAllInvertedHigh() {

        periphery.pins.each {

            it.inverted = true
            it.low()
        }


        List<Pin> pinsToBeSet = []

        8.times {
            pinsToBeSet << new Pin(number: it, address: PinPower.getPinPower(it), voltage: false)
        }

        byte result = periphery.setOutput(pinsToBeSet)

        byte expected = 0

        assert expected == result
    }

    @Test
    void setOutputAllInvertedLow() {

        periphery.pins.each {

            it.inverted = true
            it.low()
        }


        List<Pin> pinsToBeSet = []

        8.times {
            pinsToBeSet << new Pin(number: it, address: PinPower.getPinPower(it), voltage: true)
        }

        byte result = periphery.setOutput(pinsToBeSet)

        byte expected = 255

        assert expected == result
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
