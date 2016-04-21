(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('caloriesCalculatorService', caloriesCalculatorService);

    /* @ngInject */
    function caloriesCalculatorService(logger) {
        var service = {
            calculateCalories: calculateCalories,
            calculatePValue: calculatePValue,
            calculatePALValue: calculatePALValue
        };
        return service;

        ////////////////

        function calculateCalories(profile) {
            //PPM = S * P * 24
            var ppm = calculateSValue(profile.height, profile.weight) * calculatePValue(profile.age, profile.sex) * 24;
            var pal = calculatePALValue(profile.dailyActivity.id);
            var totalCaloriesDemand = Math.floor(ppm * pal);
            //logger.info(message);

            var caloriesCalculations = {
                caloriesDemand: Math.floor(ppm),
                totalCaloriesDemand: totalCaloriesDemand,
                caloriesDeficit: Math.floor(totalCaloriesDemand - ppm)
            };

            return caloriesCalculations;
        }


        function calculatePValue(age, sex) {
            var index = Math.floor(age / 10) - 1;
            var sexValue = 'male';
            if (sex == 0) {
                sexValue = 'male';
            } else {
                sexValue = 'female';
            }
            var values = [
                {male: 39.5, female: 37}, // 10-19 years
                {male: 39.5, female: 37}, // 20 -29 years
                {male: 39.5, female: 36}, // 30 - 39 years
                {male: 38.5, female: 36}, // 40 - 49 years
                {male: 37.5, female: 35}, // 50 - 59 years
                {male: 36.5, female: 34}, // 50 - 69 years
                {male: 33.5, female: 33} // 70 - 79 years
            ];

            var pValue = (values[index])[sexValue];
            return pValue;
        }

        function calculateSValue(height, weight) {
            return (0.0167 * Math.sqrt(height * weight)).toFixed(2);
        }

        function calculatePALValue(dailyActivity) {
            var values = {
                0: 1.50,//SMALL
                1: 1.80, //MEDIUM
                2: 2, //HIGH
                3: 2.30 //VERY_HIGH
            };
            return (values[dailyActivity]);
        }

        //Niska aktywność fizyczna = 1,40 – 1,69 PAL
        //średnia aktywność fizyczna = 1,70 – 1.99 PAL
        //wysoka aktywność fizyczna = 2,0 – 2,40 PAL
    }

})();


