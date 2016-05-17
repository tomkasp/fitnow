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
                data: {
                    authorities: []
                },
                views: {
                    bodysizes: {
                        templateUrl: 'scripts/app/profile/body-sizes/body-sizes.html',
                        controller: 'BodySizeController',
                        controllerAs: 'vm'
                    },
                    profile: {
                        templateUrl: 'scripts/app/profile/profile-calculator/profile.calculator.html',
                        controller: 'ProfileCalculatorController',
                        controllerAs: 'vm'
                    },
                    profileinfo:{
                        templateUrl: 'scripts/app/profile/profile-info/profile-info.view.html',
                        controller: 'ProfileInfoController',
                        controllerAs: 'vm'
                    }

                },
                resolve: {
                    loadData: ['profileInfoDataservice', function(profileInfoDataservice){
                        return profileInfoDataservice.getProfileInfoData();
                    }],
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
                    },
                    profileinfo:{
                        templateUrl: 'scripts/app/profile/profile-info/profile-info.view.html',
                        controller: 'ProfileInfoController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    loadData: ['profileInfoDataservice', function(profileInfoDataservice){
                        return profileInfoDataservice.getProfileInfoData();
                    }],
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('login');
                        return $translate.refresh();
                    }]
                }

            });
    });
