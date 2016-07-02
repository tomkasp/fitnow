(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .controller('DietController', DietController);

    /* @ngInject */
    function DietController() {
        var vm = this;

        vm.dailyActivityOptions = [
            {id: '0', name: 'Small'},
            {id: '1', name: 'Medium'},
            {id: '2', name: 'High'},
            {id: '3', name: 'Very High'}
        ];

        vm.today = function() {
            vm.dt = new Date();
        };
        vm.today();

        vm.clear = function () {
            vm.dt = null;
        };

        // Disable weekend selection
        vm.disabled = function(date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };

        vm.toggleMin = function() {
            vm.minDate = vm.minDate ? null : new Date();
        };
        vm.toggleMin();

        vm.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            vm.opened = true;
        };

        vm.dateOptions = {
            formatYear: 'yy',
            startingDay: 1,
            class: 'datepicker'
        };

        vm.initDate = new Date('2016-15-20');
        vm.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        vm.format = vm.formats[0];

        activate();

        ////////////////

        function activate() {
        }
    }

})();

