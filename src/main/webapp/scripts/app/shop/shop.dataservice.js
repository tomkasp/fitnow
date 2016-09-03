(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .factory('shopDataService', shopDataService);

    /* @ngInject */
    function shopDataService($http, exception) {
        var shopOrdersApi = '/api/shop/orders/';
        var service = {
            getOrderTypeData: getOrderTypeData

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
    }

})();

