'use strict';

angular.module('fitnowApp')
    .controller('AuthorController', function ($scope, $state, Author) {

        $scope.authors = [];
        $scope.loadAll = function() {
            Author.query(function(result) {
               $scope.authors = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.author = {
                name: null,
                id: null
            };
        };
    });
