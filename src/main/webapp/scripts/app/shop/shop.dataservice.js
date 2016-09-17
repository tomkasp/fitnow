(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .factory('shopDataService', shopDataService);

    /* @ngInject */
    function shopDataService($http, exception) {
        var shopOrdersApi = '/api/shop/orders/';
        var service = {
            getOrderTypeData: getOrderTypeData,
            createOrder: createOrder

        };
        return service;

        ////////////////

        function getOrderTypeData(type) {
            return $http.get(shopOrdersApi + type)
                .then(getPaymentsCompleted)
                .catch(getPaymentsFailed);

            function getPaymentsFailed(response) {
                if (response.status == '404') {
                    return {}
                }
                exception.catcher('XHR Failed for profile details data')
            }

            function getPaymentsCompleted(response) {
                return response.data;
            }
        }

        function createOrder(orderType, paymentIntegrationId, amount) {
            return $http.post(shopOrdersApi, buildOrder(orderType, paymentIntegrationId, amount))
                .then(getPaymentsCompleted)
                .catch(getPaymentsFailed);

            function getPaymentsFailed(response) {
                exception.catcher('Failed to save data')
            }

            function getPaymentsCompleted(response) {
                return response.data;
            }
        }

        ///////////

        function buildOrder(orderType, paymentIntegrationId, amount) {
            return {
                orderType: orderType,
                paymentIntegrationId: paymentIntegrationId,
                amount: amount
            }
        }
    }

})();

