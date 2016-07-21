(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .factory('wizardStepsModel', wizardStepsModel);


    /* @ngInject */
    function wizardStepsModel() {
        var service = {
            mealsModel: mealsModel,
            createConfiguration: createConfiguration
        };
        return service;

        ////////////////

        function createConfiguration() {

        }

        function mealsModel() {
            return {
                monday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                },
                tuesday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                },
                wednesday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                },
                thursday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                },
                friday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                },
                saturday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                },
                sunday: {
                    breakfast: true,
                    secbreakfast: true,
                    lunch: true,
                    dinner: true,
                    supper: true
                }
            };
        }
    }

})();

