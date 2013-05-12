var app = angular.module('piApp', ['ui', 'ngResource', 'demo.filters', 'demo.directives', 'demo.services', 'demo.controllers', 'demo.resources']);

app.config(['$routeProvider', function ($routeProvider) {

//    $routeProvider.when('/', {templateUrl: 'partials/list.html', controller: 'TodoCtrl'});
//    $routeProvider.otherwise({redirectTo: '/'});
}]);

app.value('version', '1.0.0-SNAPSHOT');

