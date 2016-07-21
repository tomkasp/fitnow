(function () {
    'use strict';

    angular
        .module('fitnowApp.diet')
        .factory('dietDataservice', dietDataservice);

    /* @ngInject */
    function dietDataservice($http, exception,  toaster) {

        var apiAddress = "/api/dietsurveys";
        var service = {
            getMineDiet: getMineDiet,
            createDiet: createDiet,
            updateDiet: updateDiet
            //deleteProfile: deleteProfile
        };

        return service;

        function getMineDiet() {
            return $http.get(apiAddress)
                .then(getDietComplete)
                .catch(getDietFailed);

            function getDietFailed(response) {
                if (response.status == '404') {
                    return null;
                }
                exception.catcher('XHR Failed for diet data')(message);
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
                toaster.pop('success', '', 'Diet updated');
                return response.data;
            }

            function createDietFailed(response){
                exception.catcher('Update diet failed')(response);
            }
        }

        function updateDiet(profile) {
            return $http.put(apiAddress, profile)
                .then(updateDietComplete)
                .catch(updateDietFailed);

            function updateDietComplete(response) {
                toaster.pop('success', '', 'Diet updated');
                return response.data;
            }

            function updateDietFailed(response){
                exception.catcher('Update diet failed')(response);
            }
        }

        function deleteProfile() {
        }
    }

})();

