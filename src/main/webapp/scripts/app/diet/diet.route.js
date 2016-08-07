'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app.diet', {
                url: '/diet',
                templateUrl: 'scripts/app/diet/diet.html',
                controller: 'DietController',
                controllerAs: 'vm',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('diet');
                        $translatePartialLoader.addPart('fitnowshared');
                        return $translate.refresh();
                    }]
                }
            });
    });
