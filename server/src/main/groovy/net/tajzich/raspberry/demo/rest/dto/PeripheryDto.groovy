package net.tajzich.raspberry.demo.rest.dto

import groovy.transform.CompileStatic
import net.tajzich.raspberry.demo.domain.Periphery

@CompileStatic
class PeripheryDto {

    String name
    int address
    List<PinDto> pins = []

    PeripheryDto() {
    }

    PeripheryDto(Periphery domain) {

        this.name = domain.name
        this.address = domain.address

        domain.pins.each {
            pins << new PinDto(it)
        }
    }
}
