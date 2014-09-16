package net.tajzich.raspberry.demo.rest

import net.tajzich.raspberry.demo.domain.Pin
import net.tajzich.raspberry.demo.domain.PinDevice
import net.tajzich.raspberry.demo.domain.PinPower
import net.tajzich.raspberry.demo.exception.NoSuchPinException
import net.tajzich.raspberry.demo.rest.dto.PinDto
import spock.lang.Specification

class RaspberryControllerSpec extends Specification {

    RaspberryController controller
    TestPinDevice device

    def setup() {
        controller = new RaspberryController()
        device = new TestPinDevice()
    }

    def "as is"() {

        when:

        List<Pin> result = controller.translateFrom(device, device.dtos)

        then:

        result == device.pins
    }

    def "from hi"() {

        given:

        List<PinDto> dtos = device.dtos
        dtos*.setHigh(true)

        when:

        List<Pin> result = controller.translateFrom(device, dtos)

        then:

        result.each {
            assert it.voltage
        }
    }

    def "from low"() {

        given:

        device.pins*.high()

        List<PinDto> dtos = device.dtos
        dtos*.setHigh(false)

        when:

        List<Pin> result = controller.translateFrom(device, dtos)

        then:

        result.each {
            assert !it.voltage
        }
    }


    static class TestPinDevice implements PinDevice {

        private List<Pin> pins = []

        TestPinDevice() {
            8.times {
                Pin pin = new Pin(name: "P$it", number: it, inverted: false, address: PinPower.getPinPower(it))
                pin.low()
                pins << pin
            }
        }

        @Override
        Pin getPin(int number) throws NoSuchPinException {
            return pins[number]
        }

        @Override
        List<Pin> getPins() {
            return pins
        }

        List<PinDto> getDtos() {
            return pins.collect { new PinDto(it) }
        }
    }
}
