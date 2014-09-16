package net.tajzich.raspberry.demo.exception

import groovy.transform.CompileStatic

@CompileStatic
class NoSuchPeripheryException extends CoreException {

    NoSuchPeripheryException(int address) {
        super("Cannot get periphery at address $address!")
    }
}
