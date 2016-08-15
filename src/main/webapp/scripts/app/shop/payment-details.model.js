(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .factory('paymentDetailsModel', paymentDetailsModel);

    /* @ngInject */
    function paymentDetailsModel() {
        var service = {
            build: build

        };
        return service;

        function build(paymentDetails) {
            var model = createModel();
            model.name = paymentDetails.name;
            model.surname = paymentDetails.surname;
            model.email = paymentDetails.email;
            model.sessionId = paymentDetails.sessionId;
            model.amount = paymentDetails.amount;
            model.description = paymentDetails.description;
            model.clientIp = paymentDetails.clientIp;
            model.timeStamp = paymentDetails.timeStamp;
            model.paymentSignature = paymentDetails.paymentSignature;
            return model;
        }

        ////////////////

        function createModel() {
            return {
                name: null,
                surname: null,
                email: null,
                sessionId: null,
                amount: null,
                description: null,
                clientIp: null,
                timeStamp: null,
                paymentSignature : null
            }
        }
    }

})();
