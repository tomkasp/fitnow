(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .controller('ShopController', ShopController);

    /* @ngInject */
    function ShopController(logger, loadedData, paymentDetailsModel) {
        var vm = this;

        vm.paymentDetails = {};
        activate();

        ////////////////

        function activate() {
            vm.paymentDetails = paymentDetailsModel.build(loadedData);
        }
    }

})();

