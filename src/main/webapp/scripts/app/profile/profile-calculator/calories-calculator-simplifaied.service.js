(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('caloriesCalculatorSimplifiedService', caloriesCalculatorSimplifiedService);

    /* @ngInject */
    function caloriesCalculatorSimplifiedService() {
        var service = {
            calculateCalories: calculateCalories
        };
        return service;

        ////////////////

        function calculateCalories(profile) {
            //CPM = PPM * PAL
            var ppm = calculatePPM(profile.height, profile.weight, profile.age, profile.sex);
            var pal = calculatePALValue(profile.dailyActivity.id);
            var totalCaloriesDemand = Math.floor(ppm * pal);

            var caloriesCalculations = {
                caloriesDemand: Math.floor(ppm),
                totalCaloriesDemand: totalCaloriesDemand,
                caloriesDeficit: Math.floor(totalCaloriesDemand - ppm)
            };

            return caloriesCalculations;
        }

        function calculatePPM(height, weight, age, sex) {
            return validateAndCalculatePPM(height, weight, age, sex, function () {
                var sValue = (10 * weight + 6.25 * height + 5 * age).toFixed(2);

                if (sex == 0) { //male
                    sValue = sValue + 5;
                } else { //female
                    sValue = sValue - 161;
                }

                if (isNaN(sValue)) return 0;

                return sValue;
            });

            function validateAndCalculatePPM(height, weight, age, sex, performCalculations) {
                if (height != null && weight != null && age != null && sex != null) {
                    return performCalculations();
                }
                return 0;
            }
        }


        function calculatePALValue(dailyActivity) {
            var values = {
                0: 1.20,//SMALL
                1: 1.375, //NORMAL
                2: 1.55, //MEDIUM
                3: 1.725, //HIGH
                4: 1.90 //VERY_HIGH
            };
            return (values[dailyActivity]);
        }

    }

})();

