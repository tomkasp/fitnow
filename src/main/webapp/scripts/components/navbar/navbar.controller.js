'use strict';

angular.module('fitnowApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal, ENV, profileInfoDataservice, logger) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;
        $scope.inProduction = ENV === 'prod';

        $scope.profileImg = "";

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };

        activate();

        function activate() {
            if ($scope.isAuthenticated()) {
                profileInfoDataservice.getProfileInfoData().then(function (data) {
                    $scope.profileImg = data.facebookImgUrl;
                });
            }
        }

    });
