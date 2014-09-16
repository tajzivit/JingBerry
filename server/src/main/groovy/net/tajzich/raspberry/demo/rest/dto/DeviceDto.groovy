package net.tajzich.raspberry.demo.rest.dto

import groovy.transform.CompileStatic
import net.tajzich.raspberry.demo.domain.Device

@CompileStatic
class DeviceDto {

    String name
    List<PeripheryDto> peripherals = []
    List<PinDto> pins = []

    DeviceDto() {
    }

    DeviceDto(Device domain) {

        this.name = domain.name

        domain.peripherals.each {
            peripherals << new PeripheryDto(it)
        }
    }
}
