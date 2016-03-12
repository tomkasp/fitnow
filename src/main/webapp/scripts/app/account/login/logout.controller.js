(function () {
    'use strict';

    angular
        .module('fitnowApp')
        .controller('LogOutController', LogOutController);

    /* @ngInject */
    function LogOutController($state, Auth) {
        var vm = this;
        vm.title = 'LogOutController';

        activate();

        ////////////////

        function activate() {
            Auth.logout();
            $state.go('app');
        }
    }

})();

