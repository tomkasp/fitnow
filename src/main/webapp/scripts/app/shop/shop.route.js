(function() {
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
                        standardPayment: ['shopDataService', function(shopDataService){
                            return shopDataService.getPaymentData("standard");
                        }],
                        superPayment: ['shopDataService', function(shopDataService){
                            return shopDataService.getPaymentData("super");
                        }],
                        premiumPayment: ['shopDataService', function(shopDataService){
                            return shopDataService.getPaymentData("premium");
                        }],
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('shop');
                            return $translate.refresh();
                        }]
                    }
                });
        });
})();
