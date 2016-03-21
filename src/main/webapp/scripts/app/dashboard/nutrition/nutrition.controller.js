(function () {
    'use strict';

    angular
        .module('fitnowApp.dashboard')
        .controller('NutritionController', NutritionController);


    /* @ngInject */
    function NutritionController() {
        var vm = this;
        vm.title = 'NutritionController';
        vm.calories = 1234;
        activate();

        ////////////////

        function activate() {
            console.log('nutrition controller')
        }
    }

})();

