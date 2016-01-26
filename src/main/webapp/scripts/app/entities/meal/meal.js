'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('meal', {
                parent: 'entity',
                url: '/meals',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'fitnowApp.meal.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meal/meals.html',
                        controller: 'MealController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('meal');
                        $translatePartialLoader.addPart('mealType');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('meal.detail', {
                parent: 'entity',
                url: '/meal/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'fitnowApp.meal.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/meal/meal-detail.html',
                        controller: 'MealDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('meal');
                        $translatePartialLoader.addPart('mealType');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Meal', function($stateParams, Meal) {
                        return Meal.get({id : $stateParams.id});
                    }]
                }
            })
            .state('meal.new', {
                parent: 'meal',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meal/meal-dialog.html',
                        controller: 'MealDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    type: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('meal', null, { reload: true });
                    }, function() {
                        $state.go('meal');
                    })
                }]
            })
            .state('meal.edit', {
                parent: 'meal',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meal/meal-dialog.html',
                        controller: 'MealDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Meal', function(Meal) {
                                return Meal.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meal', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('meal.delete', {
                parent: 'meal',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/meal/meal-delete-dialog.html',
                        controller: 'MealDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Meal', function(Meal) {
                                return Meal.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('meal', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
