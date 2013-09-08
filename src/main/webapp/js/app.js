var app = angular.module('chartUrls', ['ngResource', 'ngSanitize', '$strap.directives', 'ui.bootstrap']).
  config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
      when('/home', {templateUrl: 'home', controller: HomeController}).
      otherwise({redirectTo: '/home'});
  }]);