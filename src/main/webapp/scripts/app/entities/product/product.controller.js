'use strict';

angular.module('fitnowApp')
    .controller('ProductController', function ($scope, $state, Product) {

        $scope.products = [];
        $scope.loadAll = function() {
            Product.query(function(result) {
               $scope.products = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.product = {
                name: null,
                mass: null,
                id: null
            };
        };
    });
