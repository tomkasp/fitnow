(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('profileModel', profileModel);

    /* @ngInject */
    function profileModel() {
        var instance = {
            id: null,
            sex: 0,
            age: 30,
            weight: 60,
            height: 170,
            goal: {
                id: '0',
                name: 'Loose weight'
            },
            dailyActivity: {
                id: '0',
                name: 'Small'
            },
            caloriesDemand: 0,
            weightChangeQuantity: 0
        };
        return instance;
    }

})();

