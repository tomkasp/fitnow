(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('bodySizesDataservice', bodySizesDataservice);

    /* @ngInject */
    function bodySizesDataservice($http, $translate, exception, toaster) {
        var service = {
            getBodySizes: getBodySizes,
            saveBodySizes: saveBodySizes,
            getBodySizesHistory: getBodySizesHistory

        };

        var messages = {
            bodysizesUpdatedSuccess: "Body sizes updated",
            bodysizesUpdatedFailure: "Body sizes update failure",
            bodysizesRetrieveFailure: "Body sizes retrieve failure"

        };

        activate();

        return service;

        ////////////////

        function activate() {
            $translate(['profilebodysize.messages.updatedSuccess', 'profilebodysize.messages.updatedFailure', 'profilebodysize.messages.retrieveFailure'])
                .then(successCallback);

            function successCallback(translations) {
                messages.bodysizesUpdatedSuccess = translations['profilebodysize.messages.updatedSuccess'];
                messages.bodysizesUpdatedFailure = translations['profilebodysize.messages.updatedFailure'];
                messages.bodysizesRetrieveFailure = translations['profilebodysize.messages.retrieveFailure'];
            }
        }

        function getBodySizes() {
            return $http.get('/api/bodysizes')
                .then(getBodySizesCompleted)
                .catch(getBodySizesFailed);

            function getBodySizesFailed(response){
                if(response.status == '404'){
                    return {}
                }
                exception.catcher(messages.bodysizesRetrieveFailure)
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
                exception.catcher(message.bodysizesUpdatedFailure)(message);
            }

            function createBodySizeCompleted(response) {
                toaster.pop('success', '', messages.bodysizesUpdatedSuccess);
                return response.data;
            }
        }

        function updateBodySizes(bodySize) {
            return $http.put('/api/bodysizes', bodySize)
                .then(createBodySizeCompleted)
                .catch(function (message) {
                    exception.catcher(message.bodysizesUpdatedFailure)(message);
                });

            function createBodySizeCompleted(response) {
                toaster.pop('success', '', messages.bodysizesUpdatedSuccess);
                return response.data;
            }
        }

        function getBodySizesHistory(){
            return $http.get('/api/bodysizes/history')
                .then(getBodySizeHistoryCompleted)
                .catch(getBodySizeHistoryFailed);

            function getBodySizeHistoryFailed (message) {
                exception.catcher(message.bodysizesRetrieveFailure)(message);
            }

            function getBodySizeHistoryCompleted(response) {
                return response.data;
            }
        }
    }

})();

