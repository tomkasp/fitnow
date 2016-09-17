(function () {
    'use strict';

    angular
        .module('fitnowApp')
        .controller('LogInController', LogInController);

    /* @ngInject */
    function LogInController($rootScope, $scope, $state, $timeout, Auth) {
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
                if ($rootScope.previousStateName == undefined || $rootScope.previousStateName.name === 'register' || $rootScope.previousStateName.name == 'app.login') {
                    $state.go('app.profile.calculator');
                }else {
                    $state.go('app.profile.calculator');
                }
            }).catch(function (data) {
                $scope.authenticationError = true;
            });
        };



        var vm = this;
        vm.title = 'SignInController';

        activate();

        ////////////////

        function activate() {
            console.log("hello for singin")
        }

    }

})();
