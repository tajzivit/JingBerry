var services = angular.module('demo.services', []);

services.service('PeripheryService', function (PeripheryResource) {

    var service = {

        list: function (callback) {
            PeripheryResource.list(callback);
        },

        save: function (periphery, data, callback) {

            var array = [];

            if(_.isArray(data)) {
                array = data;
            } else {
                array.push(data);
            }

            PeripheryResource.save(periphery.address, array, callback);
        }
    };

    return service;

});


services.service('CurrentPeriphery', function () {

    var storage = {};

    var service = {

        set: function (data) {
            storage.periphery = data
        },

        data: storage
    };

    return service;

});
