'use strict';

angular.module('assetsApp', ['ui.bootstrap'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/recipes/', {
        templateUrl: 'views/recipes.html',
        controller: 'RecipesCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
