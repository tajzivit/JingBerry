package net.tajzich.raspberry.demo.rest

import groovy.transform.CompileStatic
import net.tajzich.raspberry.demo.domain.Device
import net.tajzich.raspberry.demo.domain.Periphery
import net.tajzich.raspberry.demo.domain.Pin
import net.tajzich.raspberry.demo.domain.PinDevice
import net.tajzich.raspberry.demo.rest.dto.DeviceDto
import net.tajzich.raspberry.demo.rest.dto.PeripheryDto
import net.tajzich.raspberry.demo.rest.dto.PinDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping('/pi')
class RaspberryController {

    @Autowired
    Device device

    @RequestMapping(value = '/device', method = RequestMethod.POST)
    public DeviceDto write(@RequestBody List<PinDto> pins) {

        device.write(translateFrom(device, pins))

        return new DeviceDto(device)
    }

    @RequestMapping(value = '/device', method = RequestMethod.GET)
    public DeviceDto device() {
        return new DeviceDto(device)
    }

    @RequestMapping(value = '/pins', method = RequestMethod.GET)
    public List<PinDto> pins() {
        return device.pins.collect { new PinDto(it)}
    }

    @RequestMapping(value = '/peripherals', method = RequestMethod.GET)
    public List<PeripheryDto> peripherals() {
        return device.peripherals.collect {new PeripheryDto(it)}
    }

    @RequestMapping(value = '/periphery/{address}', method = RequestMethod.GET)
    public PeripheryDto periphery(@PathVariable int address) {
        return new PeripheryDto(device.getPeriphery(address))
    }

    @RequestMapping(value = '/periphery/{address}', method = RequestMethod.POST)
    public PeripheryDto writeToPeriphery(@PathVariable int address, @RequestBody List<PinDto> pins) {

        Periphery periphery = device.getPeriphery(address)
        periphery.setOutput(translateFrom(periphery, pins))

        return new PeripheryDto(periphery)
    }

    protected List<Pin> translateFrom(PinDevice hasPins, List<PinDto> dtos) {

        def result = []

        dtos.each {

            Pin pin = hasPins.getPin(it.number)
            it.isHigh() ? pin.high() : pin.low()

            result << pin
        }

        return result
    }
}
