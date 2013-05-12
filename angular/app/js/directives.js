var directives = angular.module('demo.directives', []);

directives.directive('todo', function () {

    return {

        restrict: 'A',
        scope: {
            todo: '=',
            update: '&',
            delete: '&'
        },
        templateUrl: 'partials/todo.html',
        controller: function ($scope) {

            $scope.doUpdate = function () {
                $scope.update()($scope.todo);
            };

            $scope.doDelete = function () {
                $scope.delete()($scope.todo);
            };
        },
        link: function ($scope, element, attrs) {

        }
    };
});


