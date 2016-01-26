'use strict';

angular.module('fitnowApp')
    .controller('MealDetailController', function ($scope, $rootScope, $stateParams, entity, Meal, Product) {
        $scope.meal = entity;
        $scope.load = function (id) {
            Meal.get({id: id}, function(result) {
                $scope.meal = result;
            });
        };
        var unsubscribe = $rootScope.$on('fitnowApp:mealUpdate', function(event, result) {
            $scope.meal = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
