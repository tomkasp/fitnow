(function () {
    'use strict';

    angular.module('fitnowApp')
        .config(function ($stateProvider) {
            $stateProvider
                .state('app.shop', {
                    url: '/shop',
                    templateUrl: 'scripts/app/shop/shop.html',
                    controller: 'ShopController',
                    controllerAs: 'vm',
                    resolve: {
                        standardPayment: ['shopDataService', function (shopDataService) {
                            return shopDataService.getOrderTypeData("standard");
                        }],
                        superPayment: ['shopDataService', function (shopDataService) {
                            return shopDataService.getOrderTypeData("super");
                        }],
                        premiumPayment: ['shopDataService', function (shopDataService) {
                            return shopDataService.getOrderTypeData("premium");
                        }],
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('shop');
                            return $translate.refresh();
                        }]
                    }
                })
                .state('app.success', {
                    url: '/shop/success',
                    templateUrl: 'scripts/app/shop/paymentsuccess.html',
                    resolve: {
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('shop');
                            return $translate.refresh();
                        }]
                    }
                })
                .state('app.failure', {
                    url: '/shop/failure',
                    templateUrl: 'scripts/app/shop/paymentfailure.html',
                    resolve: {
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('shop');
                            return $translate.refresh();
                        }]
                    }
                });
        });
})();
