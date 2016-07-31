(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .factory('dietModel', dietModel);


    /* @ngInject */
    function dietModel() {

        var service = {
            buildStepOneObject: buildStepOneObject,
            buildStepThreeObject: buildStepThreeObject,
            buildTransferObject: buildTransferObject
        };
        return service;

        ////////////////

        function buildStepOneObject(dietSurvey) {
            var model = stepOneModelProvider();
            if (dietSurvey.id != null) {
                model.id = dietSurvey.id;
                model.bornDate = new Date(dietSurvey.bornDate.replace(/-/g, "/"));//dietSurvey.bornDate;
                model.wakeup.min = dietSurvey.wakeupMin;
                model.wakeup.max = dietSurvey.wakeupMax;
                model.workhours.min = dietSurvey.workMin;
                model.workhours.max = dietSurvey.workMax;
            }
            model.sex = dietSurvey.sex;
            model.dailyActivity = dietSurvey.dailyActivity;
            model.height = dietSurvey.height;
            return model;
        }

        function buildStepThreeObject(dietSurvey) {
            var model = stepThreeModelProvider();
            model.isAllergy = dietSurvey.isAllergy;
            model.allergyDetails = dietSurvey.allergyDetails;
            model.isFoodIntolerance = dietSurvey.isFoodIntolerance;
            model.foodIntolleranceDetails = dietSurvey.foodIntolleranceDetails;
            model.favorites = dietSurvey.favorites;
            model.isLikingSoup = dietSurvey.isLikingSoup;
            model.foodExclusion = dietSurvey.foodExclusion;
            model.additionalInfo = dietSurvey.additionalInfo;
            return model;
        }

        function buildTransferObject(stepOne, stepTwo, stepThree) {
            return {
                id: stepOne.id,
                height: stepOne.height,
                dailyActivity: stepOne.dailyActivity,
                bornDate: stepOne.bornDate.getFullYear() + '-' + (stepOne.bornDate.getMonth() + 1) + '-' + stepOne.bornDate.getDate(),
                wakeupMin: stepOne.wakeup.min,
                wakeupMax: stepOne.wakeup.max,
                workMin: stepOne.workhours.min,
                workMax: stepOne.workhours.max,
                sex: stepOne.sex,
                mealQuantity: stepTwo,
                isAllergy: stepThree.isAllergy,
                allergyDetails: stepThree.allergyDetails,
                isFoodIntolerance: stepThree.isFoodIntolerance,
                foodIntolleranceDetails: stepThree.foodIntolleranceDetails,
                favorites: stepThree.favorites,
                isLikingSoup: stepThree.isLikingSoup,
                foodExclusion: stepThree.foodExclusion,
                additionalInfo: stepThree.additionalInfo
            }
        }

        function stepOneModelProvider() {
            return {
                id: null,
                dailyActivity: null,
                height: null,
                sex: 0,
                bornDate: new Date(),
                wakeup: {min: 3, max: 8},
                workhours: {min: 3, max: 8}
            }
        }

        function stepThreeModelProvider() {
            return {
                isAllergy: false,
                allergyDetails: '',
                isFoodIntolerance: false,
                foodIntolleranceDetails: '',
                favorites: '',
                isLikingSoup: false,
                foodExclusion: '',
                additionalInfo: ''


            }
        }
    }

})();


//private Long id;
//private Integer height;
//private DailyActivity dailyActivity;
//private DateTime bornDate;
//private String sex;
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

