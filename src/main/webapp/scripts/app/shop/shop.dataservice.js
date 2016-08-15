(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .factory('shopDataService', shopDataService);

    /* @ngInject */
    function shopDataService($http, exception) {
        var service = {
            getPaymentData: getPaymentData

        };
        return service;

        ////////////////

        function getPaymentData() {
            return $http.get('/api/payments')
                .then(getPaymentsCompleted)
                .catch(getPaymentsFailed);

            function getPaymentsFailed(response){
                if(response.status == '404'){
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

