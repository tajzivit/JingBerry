package net.tajzich.raspberry.demo.rest

import net.tajzich.raspberry.demo.domain.Device
import net.tajzich.raspberry.demo.domain.Periphery
import net.tajzich.raspberry.demo.domain.Pin
import net.tajzich.raspberry.demo.rest.dto.DeviceDto
import net.tajzich.raspberry.demo.rest.dto.PeripheryDto
import net.tajzich.raspberry.demo.rest.dto.PinDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/8/13
 */
@Controller
@RequestMapping('/pi')
class RaspberryController {

    Device device

    @RequestMapping(value = '/device', method = RequestMethod.POST)
    @ResponseBody
    public DeviceDto write(@RequestBody List<PinDto> pins) {

        device.write(translateFrom(device, pins))

        return new DeviceDto(device)
    }

    @RequestMapping(value = '/device', method = RequestMethod.GET)
    @ResponseBody
    public DeviceDto device() {
        return new DeviceDto(device)
    }

    @RequestMapping(value = '/pins', method = RequestMethod.GET)
    @ResponseBody
    public List<PinDto> pins() {

        def result = []

        device.pins.each {
            result << new PinDto(it)
        }

        return result
    }

    @RequestMapping(value = '/peripherals', method = RequestMethod.GET)
    @ResponseBody
    public List<PeripheryDto> peripherals() {

        def result = []

        device.peripherals.each {
            result << new PeripheryDto(it)
        }

        return result
    }

    @RequestMapping(value = '/periphery/{address}', method = RequestMethod.GET)
    @ResponseBody
    public PeripheryDto periphery(@PathVariable int address) {
        return new PeripheryDto(device.getPeriphery(address))
    }

    @RequestMapping(value = '/periphery/{address}', method = RequestMethod.POST)
    @ResponseBody
    public PeripheryDto writeToPeriphery(@PathVariable int address, @RequestBody List<PinDto> pins) {

        Periphery periphery = device.getPeriphery(address)
        periphery.setOutput(translateFrom(periphery, pins))

        return new PeripheryDto(periphery)
    }

    private List<Pin> translateFrom(def hasPins, List<PinDto> dtos) {

        def result = []

        dtos.each {

            Pin pin = hasPins.getPin(it.number)
            it.isHigh() ? pin.high() : pin.low()

            result << pin
        }

        return result
    }


    @Autowired
    public void setDevice(Device device) {
        this.device = device
    }
}
