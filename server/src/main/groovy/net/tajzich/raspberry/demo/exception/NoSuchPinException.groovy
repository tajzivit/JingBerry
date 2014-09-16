package net.tajzich.raspberry.demo.exception

import groovy.transform.CompileStatic

@CompileStatic
class NoSuchPinException extends CoreException {

    NoSuchPinException(int number) {
        super("Device doesn't have pin for $number!")
    }
}
