(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('ProfileCalculatorController', ProfileCalculatorController);


    /* @ngInject */
    function ProfileCalculatorController($scope, $translate, profileModel, logger,
                                         profileDataservice, caloriesCalculatorService, caloriesCalculatorSimplifiedService) {
        var vm = this;

        vm.submit = submit;
        vm.userProfileFormSubmitted = false;


        vm.userProfile = profileModel;

        vm.sexOptions = [
            {id: 0, name: 'Male', enumValue: 'MALE'},
            {id: 1, name: 'Female', enumValue: 'FEMALE'}
        ];

        vm.goalOptions = [
            {id: '0', name: 'fitnowshared.profile.goalloose', enumValue: 'LOOSE'},
            {id: '1', name: 'fitnowshared.profile.goalgain', enumValue: 'GAIN'}
        ];

        vm.dailyActivityOptions = [
            {id: '0', name: 'fitnowshared.profile.small'},
            {id: '1', name: 'fitnowshared.profile.medium'},
            {id: '2', name: 'fitnowshared.profile.high'},
            {id: '3', name: 'fitnowshared.profile.veryhigh'}
        ];

        $scope.$watch('vm.userProfile', function (newValue, oldValue) {
            var caloriesCalculations = caloriesCalculatorSimplifiedService.calculateCalories(vm.userProfile);
            vm.userProfile.totalCaloriesDemand = caloriesCalculations.totalCaloriesDemand;
            vm.userProfile.caloriesDemand = caloriesCalculations.caloriesDemand;
            vm.userProfile.caloriesDeficit = caloriesCalculations.caloriesDeficit;
        }, true);


        activate();

        ////////////////

        function activate() {
            return getProfile();

        }

        angular.element("#slider").on('slideStop', function (data) {
            updateModel(data.value);
        });

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
            vm.userProfileFormSubmitted = true;
            if (vm.userProfileForm.$valid) {
                vm.userProfile.caloriesDemand = caloriesCalculatorService.calculateCalories(vm.userProfile);
                if (vm.userProfile.id == null) {
                    profileDataservice.createProfile(vm.userProfile).then(function (response) {
                        vm.userProfile.id = response.id;
                    });
                } else {
                    profileDataservice.updateProfile(vm.userProfile);
                }
            } else {
                $translate('profilecalculator.messeges.formerror').then(function (formError) {
                    logger.error(formError)
                });
            }
        }
    }

})();

