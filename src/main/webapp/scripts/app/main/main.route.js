'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider
            .otherwise('/app');
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
            })
            .state('app.todo', {
                url: '/todo',
                templateUrl: 'tpl/apps_todo.html',
                //resolve: load(['js/app/todo/todo.js', 'moment'])
            })
        ;
    });
