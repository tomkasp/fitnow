(function () {
    'use strict';

    angular.module('fitnowApp')
        .config(function ($stateProvider) {
            $stateProvider
                .state('app.dashboard', {
                    url: '/dashboard',
                    abstract: true,
                    templateUrl: 'scripts/app/dashboard/dashboard.view.html',
                    resolve: {
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('dashboard');
                            return $translate.refresh();
                        }]
                    }
                })
                .state('app.dashboard.details', {
                    url: '',
                    views: {
                        view1: {
                            templateUrl: 'scripts/app/dashboard/index.viewB.html'
                        },
                        nutrition: {
                            templateUrl: 'scripts/app/dashboard/nutrition/nutrition.view.html',
                            controller: 'NutritionController',
                            controllerAs: 'vm'
                        },
                        nutritionGraph: {
                            templateUrl: 'scripts/app/dashboard/nutrition/nutrition-graph.view.html'
                        }
                    }
                })
        });
})();
