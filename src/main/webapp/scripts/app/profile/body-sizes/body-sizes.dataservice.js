(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('bodySizesDataservice', bodySizesDataservice);

    /* @ngInject */
    function bodySizesDataservice($http, $location, exception, toaster) {
        var service = {
            getBodySizes: getBodySizes,
            saveBodySizes: saveBodySizes,
            getBodySizesHistory: getBodySizesHistory

        };
        return service;

        ////////////////

        function getBodySizes() {
            return $http.get('/api/bodysizes')
                .then(getBodySizesCompleted)
                .catch(getBodySizesFailed);

            function getBodySizesFailed(response){
                if(response.status == '404'){
                    return {}
                }
                exception.catcher('XHR Failed for profile details data')
            }

            function getBodySizesCompleted(response) {
                return response.data;
            }
        }

        function saveBodySizes(bodySizes){
            if(bodySizes.id != null){
                return updateBodySizes(bodySizes);
            }
            return createBodySizes(bodySizes);
        }

        function createBodySizes(bodySize) {
            return $http.post('/api/bodysizes', bodySize)
                .then(createBodySizeCompleted)
                .catch(createdBodySizedFailed);

            function createdBodySizedFailed (message) {
                exception.catcher('Failed create body size')(message);
            }

            function createBodySizeCompleted(response) {
                toaster.pop('success', '', 'Body sizes updated');
                return response.data;
            }
        }

        function updateBodySizes(bodySize) {
            return $http.put('/api/bodysizes', bodySize)
                .then(createBodySizeCompleted)
                .catch(function (message) {
                    exception.catcher('XHR Failed update body size')(message);
                });

            function createBodySizeCompleted(response) {
                toaster.pop('success', '', 'Body sizes updated');
                return response.data;
            }
        }

        function getBodySizesHistory(){
            return $http.get('/api/bodysizes/history')
                .then(getBodySizeHistoryCompleted)
                .catch(getBodySizeHistoryFailed);

            function getBodySizeHistoryFailed (message) {
                exception.catcher('Failed to get body size history')(message);
            }

            function getBodySizeHistoryCompleted(response) {
                return response.data;
            }
        }
    }

})();

