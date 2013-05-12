var controllers = angular.module('demo.controllers', []);

controllers.controller('PeripheralsCtrl', function ($scope, CurrentPeriphery, PeripheryService) {

    PeripheryService.list(function (data) {
        $scope.peripherals = data;
    })

    $scope.select = function (periphery) {
        CurrentPeriphery.set(periphery)

        console.log("selected: ");
        console.log(periphery);
    };

});

controllers.controller('PinsCtrl', function ($scope, CurrentPeriphery, PeripheryService) {
    $scope.data = CurrentPeriphery.data;

    $scope.toggle = function (pin) {

        PeripheryService.save($scope.data.periphery, pin, function (data) {
            console.log('success:');
            console.log(data);
        });
    };

    $scope.toggleAll = function () {

        _.each($scope.data.periphery.pins, function (pin) {
            pin.high = !pin.high;
        });

        PeripheryService.save($scope.data.periphery, $scope.data.periphery.pins, function (data) {
            console.log('success:');
            console.log(data);
        });
    };
});
