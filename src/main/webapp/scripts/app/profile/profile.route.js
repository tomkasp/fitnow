'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app.profile', {
                url: '/profile',
                templateUrl: 'scripts/app/profile/profile-container.html'
            })
            .state('app.profile.calculator', {
                url: '/calculator',
                views: {
                    bodysizes: {
                        templateUrl: 'scripts/app/profile/body-sizes/body-sizes.html',
                        controller: 'BodySizeController',
                        controllerAs: 'vm'
                    },
                    profile: {
                        templateUrl: 'scripts/app/profile/calculator/profile.calculator.html',
                        controller: 'ProfileCalculatorController',
                        controllerAs: 'vm'
                    }
                },
                //templateUrl: 'scripts/app/profile/user-profile.html',
                //controller: 'UserProfileController',
                //controllerAs: 'vm',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('login');
                        return $translate.refresh();
                    }]
                }
            })
            .state('app.profile.progress', {
                url: '/progress',
                views: {
                    bodysizes: {
                        templateUrl: 'scripts/app/profile/body-sizes/body-sizes.html',
                        controller: 'BodySizeController',
                        controllerAs: 'vm'
                    },
                    profile: {
                        templateUrl: 'scripts/app/profile/progress/profile.progress.html'
                    }
                }
            });
        //.state('app.profile.profileQuestions', {
        //    url: '/questions',
        //    templateUrl: 'scripts/app/profile/questions-form.html',
        //    resolve: {
        //        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
        //            $translatePartialLoader.addPart('login');
        //            return $translate.refresh();
        //        }]
        //    }
        //})
    });
