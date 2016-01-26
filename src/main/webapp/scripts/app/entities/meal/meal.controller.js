'use strict';

angular.module('fitnowApp')
    .controller('MealController', function ($scope, $state, Meal) {

        $scope.meals = [];
        $scope.loadAll = function() {
            Meal.query(function(result) {
               $scope.meals = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.meal = {
                type: null,
                id: null
            };
        };
    });
