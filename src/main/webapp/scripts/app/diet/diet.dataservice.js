(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .factory('dietDataservice', dietDataservice);

    /* @ngInject */
    function dietDataservice($http, $translate, exception, toaster) {

        var apiAddress = "/api/dietsurveys";
        var messages = {
            dietUpdatedSuccess: "Diet updated",
            dietUpdatedFailure: "Diet update failure",
            dietRetrieveFailure: "Diet retrieve failure"

        };
        var service = {
            getMineDiet: getMineDiet,
            createDiet: createDiet,
            updateDiet: updateDiet
        };

        activate();

        return service;

        function activate() {
            $translate(['diet.updatedSuccess', 'diet.updatedFailure', 'diet.retrieveFailure'])
                .then(successCallback);

            function successCallback(translations) {
                    messages.dietUpdatedSuccess = translations['diet.updatedSuccess'];
                    messages.dietUpdatedFailure = translations['diet.updatedFailure'];
                    messages.dietRetrieveFailure = translations['diet.retrieveFailure'];
            }
        }


        function getMineDiet() {

            return $http.get(apiAddress)
                .then(getDietComplete)
                .catch(getDietFailed);

            function getDietFailed(response) {
                if (response.status == '404') {
                    return null;
                }
                exception.catcher(messages.dietRetrieveFailure)(message);
            }

            function getDietComplete(response) {
                return response.data;
            }
        }

        function createDiet(profile) {
            return $http.post(apiAddress, profile)
                .then(createDietComplete)
                .catch(createDietFailed);

            function createDietComplete(response) {
                toaster.pop('success', '', messages.dietUpdatedSuccess);
                return response.data;
            }

            function createDietFailed(response) {
                exception.catcher(messages.dietUpdatedFailure)(response);
            }
        }

        function updateDiet(profile) {
            return $http.put(apiAddress, profile)
                .then(updateDietComplete)
                .catch(updateDietFailed);

            function updateDietComplete(response) {
                toaster.pop('success', '', messages.dietUpdatedSuccess);
                return response.data;
            }

            function updateDietFailed(response) {
                exception.catcher(messages.dietUpdatedFailure)(response);
            }
        }
    }

})();

