(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('UserProfileController', UserProfileController);


    /* @ngInject */
    function UserProfileController() {
        var vm = this;
        vm.submit = submit;
        vm.getCssClasses = getCssClasses;
        vm.showError = showError;

        vm.userProfile = {
            sex: 0,
            age: 0,
            weight: 0,
            height: 0,
            goal: {
                id: '0',
                name: 'Loose weight'
            },
            caloriesDemand: 2500,
            weightChangeQuantity: 0
        };

        vm.goalOptions =  [
            {id: '0', name: 'Loose weight'},
            {id: '1', name: 'Gain muscles'}
        ];

        activate();

        ////////////////

        function activate() {

        }

        function submit() {
            console.log(vm);
            console.log('submit');
        }

         function getCssClasses (ngModelController) {
            return {
                error: ngModelController.$invalid && ngModelController.$dirty,
                success: ngModelController.$valid && ngModelController.$dirty
            };
        }

        function showError(ngModelController, error) {
            return ngModelController.$error[error];
        }
    }

})();

