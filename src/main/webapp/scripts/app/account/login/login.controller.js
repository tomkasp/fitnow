'use strict';

angular.module('fitnowApp')
    .controller('LoginController', function ($rootScope, $scope, $state, $timeout, Auth, $resource) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = true;
        $timeout(function (){angular.element('[ng-model="username"]').focus();});
        $scope.login = function (event) {
            event.preventDefault();
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                if ($rootScope.previousStateName === 'register') {
                    $state.go('app');
                } else {
                    $rootScope.back();
                }
            }).catch(function () {
                $scope.authenticationError = true;
            });
        };

        $scope.facebookLogin = function(){
            var Facebook = $resource('/connect/facebook', {}, {
                'save': { method:'POST' }
            });

            var user = Facebook.save({}, function() {
                console.log("test");
            });
        }
    });

//scope" value="user_posts
