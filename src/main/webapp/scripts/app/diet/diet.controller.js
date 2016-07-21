(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .controller('DietController', DietController);

    /* @ngInject */
    function DietController(uibDatepickerPopupConfig, dietDataservice, logger, wizardStepsModel, dietModel) {
        var vm = this;
        vm.open = open;

        vm.clicktest = function () {

            logger.log('', vm.step3);
        };

        vm.steps = {percent: 20, step1: true, step2: false, step3: false};

        vm.dailyActivityOptions = [
            {id: '0', name: 'Small'},
            {id: '1', name: 'Medium'},
            {id: '2', name: 'High'},
            {id: '3', name: 'Very High'}
        ];

        vm.step1 = {
            dailyActivity: vm.dailyActivityOptions[0],
            sex: 0,
            bornDate: new Date(),
            wakeup: {min: 3, max: 8},
            workhours: {min: 3, max: 8}
        };
        vm.step3 = {
            soup: 0,
            allergy: 1,
            intolerance: 1,
            allergyDetails: ''
        };
        vm.step2 = wizardStepsModel.mealsModel();

        vm.dateOptions = {
            formatYear: 'yy',
            startingDay: 1,
            class: 'datepicker',
            showButtonBar: false
        };

        vm.configuration = {};

        activate();

        ////////////////

        function activate() {
            uibDatepickerPopupConfig.showButtonBar = false;
            dietDataservice.getMineDiet().then(function (response) {
                vm.step1 = dietModel.buildStepOneObject(response);
            });
        }

        function open($event) {
            $event.preventDefault();
            $event.stopPropagation();
            vm.opened = true;
        }
    }

})();

