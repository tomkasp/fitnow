(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .controller('DietController', DietController);

    /* @ngInject */
    function DietController(uibDatepickerPopupConfig,
                            dietDataservice, logger, wizardStepsModel, dietModel, Utility, $translate) {

        var vm = this;
        vm.open = open;
        vm.steps = {percent: 20, step1: true, step2: false, step3: false};
        vm.dailyActivityOptions = [
            {id: '0', name: 'fitnowshared.profile.small'},
            {id: '1', name: 'fitnowshared.profile.medium'},
            {id: '2', name: 'fitnowshared.profile.high'},
            {id: '3', name: 'fitnowshared.profile.veryhigh'}
        ];
        vm.step1 = {
            dailyActivity: vm.dailyActivityOptions[0],
            sex: 0,
            bornDate: new Date(),
            wakeup: {min: 3, max: 8},
            workhours: {min: 3, max: 8}
        };
        vm.step2 = wizardStepsModel.mealsModel();
        vm.step3 = {};

        vm.submitDietInfo = submitDietInfo;
        vm.goToStepTwo = goToStepTwo;

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
                vm.step2 = response.mealQuantity;
                vm.step3 = dietModel.buildStepThreeObject(response);
            });
        }

        function open($event) {
            $event.preventDefault();
            $event.stopPropagation();
            vm.opened = true;
        }

        function submitDietInfo() {
            vm.steps.percent = 100;
            var modelToTransfer = dietModel.buildTransferObject(vm.step1, vm.step2, vm.step3);
            if (Utility.checkIfUndefinedOrNull(vm.step1.id)) {
                dietDataservice.createDiet(modelToTransfer).then(function (response) {
                    vm.step1.id = response.id;
                });
            } else {
                dietDataservice.updateDiet(modelToTransfer);
            }
        }

        function goToStepTwo() {
            vm.step1.submitted = true;
            if (vm.formStep1.$valid) {
                vm.steps.step2 = true;
            }
            else {
                vm.steps.step2 = false;
            }
        }

    }

})();

