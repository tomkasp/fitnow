(function () {
    'use strict';

    angular
        .module('fitnowApp.shop')
        .controller('ShopController', ShopController);

    /* @ngInject */
    function ShopController() {
        var vm = this;
        vm.title = 'ShopController';

        activate();

        ////////////////

        function activate() {
        }
    }

})();

