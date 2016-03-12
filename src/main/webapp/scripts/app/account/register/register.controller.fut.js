(function () {
    'use strict';

    angular
        .module('fitnowApp')
        .controller('RegisterControllerFut', RegisterControllerFut);


    /* @ngInject */
    function RegisterControllerFut($translate, $timeout, Auth) {
        var vm = this;
        vm.success = null;
        vm.error = null;
        vm.doNotMatch = null;
        vm.errorUserExists = null;
        vm.registerAccount = {};
        $timeout(function (){angular.element('[ng-model="registerAccount.login"]').focus();});

        vm.register = register;

        activate();

        ////////////////

        function activate() {
        }

        function register() {
            if (vm.registerAccount.password !== vm.confirmPassword) {
                vm.doNotMatch = 'ERROR';
            } else {
                vm.registerAccount.langKey = $translate.use();
                vm.doNotMatch = null;
                vm.error = null;
                vm.errorUserExists = null;
                vm.errorEmailExists = null;

                Auth.createAccount(vm.registerAccount).then(function () {
                    vm.success = 'OK';
                }).catch(function (response) {
                    vm.success = null;
                    if (response.status === 400 && response.data === 'login already in use') {
                        vm.errorUserExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                        vm.errorEmailExists = 'ERROR';
                    } else {
                        vm.error = 'ERROR';
                    }
                });
            }
        }
    }

})();

