(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('ProfileCalculatorController', ProfileCalculatorController);


    /* @ngInject */
    function ProfileCalculatorController($scope, profileModel, logger, profileDataservice, caloriesCalculatorService) {
        var vm = this;
        vm.submit = submit;
        vm.getCssClasses = getCssClasses;
        vm.showError = showError;
        vm.test = click;
        vm.canSave = canSave;

        function click() {
            profileDataservice.getMineProfile().then(function (response) {
                vm.userProfile = response.data;
            });
        }

        vm.userProfile = profileModel;

        vm.sexOptions = [
            {id: 0, name: 'Male', enumValue: 'MALE'},
            {id: 1, name: 'Female', enumValue: 'FEMALE'}
        ];

        vm.goalOptions = [
            {id: '0', name: 'Loose weight', enumValue: 'LOOSE'},
            {id: '1', name: 'Gain muscles', enumValue: 'GAIN'}
        ];

        vm.dailyActivityOptions = [
            {id: '0', name: 'Small'},
            {id: '1', name: 'Medium'},
            {id: '2', name: 'High'},
            {id: '3', name: 'Very High'}
        ];

        $scope.$watch('vm.userProfile', function (newValue, oldValue) {
            var caloriesCalculations = caloriesCalculatorService.calculateCalories(vm.userProfile);
            vm.userProfile.totalCaloriesDemand = caloriesCalculations.totalCaloriesDemand;
            vm.userProfile.caloriesDemand = caloriesCalculations.caloriesDemand;
            vm.userProfile.caloriesDeficit = caloriesCalculations.caloriesDeficit;
        }, true);

        angular.element("#slider").on('slideStop', function (data) {
            updateModel(data.value);
        });

        activate();

        ////////////////

        function activate() {
            return getProfile(); //profileDataservice.getMineProfile();
            //vm.userProfile.caloriesDemand = caloriesCalculatorService.calculateCalories(vm.userProfile);
        }

        function getProfile() {
            return profileDataservice.getMineProfile().then(function (data) {
                vm.userProfile = data;
            })
        }

        function updateModel(val) {
            $scope.$apply(function () {
                vm.userProfile.weightChangeQuantity = val;
            });
        }

        function submit() {
            vm.userProfile.caloriesDemand = caloriesCalculatorService.calculateCalories(vm.userProfile);
            if (vm.userProfile.id == null) {
                profileDataservice.createProfile(vm.userProfile);
            } else {
                profileDataservice.updateProfile(vm.userProfile);
            }
        }

        function getCssClasses(ngModelController) {
            return {
                error: ngModelController.$invalid && ngModelController.$dirty,
                success: ngModelController.$valid && ngModelController.$dirty
            };
        }

        function showError(ngModelController, error) {
            return ngModelController.$error[error];
        }

        function canSave() {
            return $scope.userProfileForm.$dirty && $scope.userProfileForm.$valid;
        }
    }

})();

