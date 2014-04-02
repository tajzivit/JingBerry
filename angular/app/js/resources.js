var resources = angular.module('demo.resources', []);

resources.constant('raspUrl', 'http://raspberrypi.local:8080/pi');

resources.factory('PeripheryResource', function ($http, raspUrl) {

    return {

        list: function (callback) {

            $http({
                method: 'GET',
                url: raspUrl + '/peripherals'
            }).success(callback);
        },

        save: function (address, data, callback) {

            $http({
                method: 'POST',
                url: raspUrl + '/periphery/' + address,
                data: angular.toJson(data),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(callback);
        }
    };

    return $resource(raspUrl + '/periphery/:address', {}, {
        'save': {isArray: true}
    });
});
