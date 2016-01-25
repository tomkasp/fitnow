'use strict';

angular.module('fitnowApp')
    .controller('ProductDetailController', function ($scope, $rootScope, $stateParams, entity, Product) {
        $scope.product = entity;
        $scope.load = function (id) {
            Product.get({id: id}, function(result) {
                $scope.product = result;
            });
        };
        var unsubscribe = $rootScope.$on('fitnowApp:productUpdate', function(event, result) {
            $scope.product = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
