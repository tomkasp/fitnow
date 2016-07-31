'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider
            .otherwise('/app/profile/calculator');
        $stateProvider
            .state('app', {
                parent: 'site',
                //abstract: true,
                url: '/app',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        controller: 'MainController',
                        templateUrl: 'scripts/app/main/main.html',
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        $translatePartialLoader.addPart('nav');
                        return $translate.refresh();
                    }]
                }
            });
    });
