(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .factory('dietModel', dietModel);


    /* @ngInject */
    function dietModel() {

        var service = {
            buildStepOneObject: buildStepOneObject,
        };
        return service;

        ////////////////

        function buildStepOneObject(dietSurvey) {
            var model = stepOneModelProvider();
            if (dietSurvey.id != null) {
                model.id = dietSurvey.id;
                model.dailyActivity = dietSurvey.dailyActivity;
                model.sex = dietSurvey.sex;
                model.bornDate = dietSurvey.bornDate;
                model.wakeup.min = dietSurvey.wakeupMin;
                model.wakeup.max = dietSurvey.wakeupMax;
                model.workhours.min = dietSurvey.workMin;
                model.workhours.max = dietSurvey.workMax;
            }
            return model;
        }

        function stepOneModelProvider() {
            return {
                id: null,
                dailyActivity: null,
                sex: 0,
                bornDate: new Date(),
                wakeup: {min: 3, max: 8},
                workhours: {min: 3, max: 8}
            }
        }
    }

})();

//private Long id;
//private Integer height;
//private Integer age;
//private Sex sex;
//private Integer wakeupMin;
//private Integer wakeupMax;
//private Integer workMin;
//private Integer workMax;
//private String mealQuantity; // JSON based field
//private Boolean isAllergy;
//private String allergyDetails;
//private Boolean isFoodIntolerance;
//private String foodIntolleranceDetails;
//private String favorites;
//private Boolean isLikingSoup;
//private String foodExclusion;
//private String additionalInfo;

