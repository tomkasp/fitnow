(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .controller('ShopController', ShopController);

    /* @ngInject */
    function ShopController(shopDataService, logger, standardPayment, superPayment, premiumPayment, paymentDetailsModel) {
        var vm = this;

        vm.standardPaymentDetails = {};
        vm.superPaymentDetails = {};
        vm.premiumPaymentDetails = {};

        vm.test = standardOrder;
        activate();

        ////////////////

        function activate() {
            vm.standardPaymentDetails = paymentDetailsModel.build(standardPayment);
            vm.superPaymentDetails = paymentDetailsModel.build(superPayment);
            vm.premiumPaymentDetails = paymentDetailsModel.build(premiumPayment);
        }

        function standardOrder() {
            shopDataService.createOrder(standardPayment.description, standardPayment.orderId, standardPayment.amount);
        }
    }

})();

