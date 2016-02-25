'use strict';

angular.module('fitnowApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('connections', {
                parent: 'account',
                url: '/social/connections',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'global.menu.account.connections'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/login/social/connections.html',
                        controller: 'ConnectionsController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('connections');
                        return $translate.refresh();
                    }]
                }
            });
    });
