(function () {
    'use strict';

    angular
        .module('fitnowApp.dashboard')
        .controller('DashboardController', DashboardController);

    /* @ngInject */
    function DashboardController() {
        var vm = this;
        vm.title = 'DashboardController';

        activate();

        ////////////////

        function activate() {
        }
    }

})();

