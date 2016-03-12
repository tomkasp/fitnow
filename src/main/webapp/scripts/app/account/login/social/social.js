'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('register-social', {
                parent: 'account',
                url: '/register-social',
                data: {
                    roles: [],
                    pageTitle: 'social.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/login/social/register-social.html',
                        controller: 'RegisterSocialController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('social');
                        return $translate.refresh();
                    }]
                }
            })
            .state('app.register-social', {
                url: '/register-social2',
                templateUrl: 'scripts/app/account/login/social/register-social-new.html',
                controller: 'RegisterSocialController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('social');
                        return $translate.refresh();
                    }]
                }
            });
    });
