(function () {
    'use strict';

    angular
        .module('fitnowApp')
        .controller('SignInController', SignInController);

    /* @ngInject */
    function SignInController($rootScope, $scope, $state, $timeout, Auth, $resource) {
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



        var vm = this;
        vm.title = 'SignInController';

        activate();

        ////////////////

        function activate() {
            console.log("hello for singin")
        }
    }

})();


//
//'use strict';
//
///* Controllers */
//  // signin controller
//app.controller('SigninFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {
//    $scope.user = {};
//    $scope.authError = null;
//    $scope.login = function() {
//      $scope.authError = null;
//      // Try to login
//      $http.post('api/login', {email: $scope.user.email, password: $scope.user.password})
//      .then(function(response) {
//        if ( !response.data.user ) {
//          $scope.authError = 'Email or Password not right';
//        }else{
//          $state.go('app.dashboard-v1');
//        }
//      }, function(x) {
//        $scope.authError = 'Server Error';
//      });
//    };
//  }])
//;
