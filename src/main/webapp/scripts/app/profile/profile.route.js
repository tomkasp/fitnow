'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app.profile', {
                url: '/profile',
                templateUrl: 'scripts/app/profile/container.html'
            })
            .state('app.profile.settings', {
                url: '/settings',
                templateUrl: 'scripts/app/profile/user-profile.html',
                controller: 'UserProfileController',
                controllerAs: 'vm',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('login');
                        return $translate.refresh();
                    }]
                }
            })
            .state('app.profile.profileQuestions', {
                url: '/questions',
                templateUrl: 'scripts/app/profile/questions-form.html',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('login');
                        return $translate.refresh();
                    }]
                }
            })
    });
