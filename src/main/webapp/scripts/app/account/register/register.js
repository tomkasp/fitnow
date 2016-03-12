'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('register', {
                parent: 'account',
                url: '/register',
                data: {
                    authorities: [],
                    pageTitle: 'register.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/register/register.html',
                        controller: 'RegisterController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('register');
                        return $translate.refresh();
                    }]
                }
            })
            .state('app.register', {
                url: '/register',
                templateUrl: 'scripts/app/account/register/page_register.html',
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
