'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app.register', {
                url: '/register',
                templateUrl: 'scripts/app/account/register/register.html',
                controller: 'RegisterControllerFut',
                controllerAs: 'vm',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader
                            .addPart('register')
                            .addPart('social');
                        return $translate.refresh();
                    }]
                }
            });
    });
