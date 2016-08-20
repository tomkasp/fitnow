(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('profileDataservice', profileDataservice);

    /* @ngInject */
    function profileDataservice($http, $translate, exception, profileModel, toaster) {

        var service = {
            getMineProfile: getMineProfile,
            createProfile: createProfile,
            updateProfile: updateProfile
        };

        var messages = {
            profileUpdatedSuccess: "Profile updated",
            profileUpdatedFailure: "Profile update failure",
            profileRetrieveFailure: "Profile retrieve failure"

        };

        activate();

        return service;

        function activate() {
            $translate(['profilecalculator.messages.updatedSuccess', 'profilecalculator.messages.updatedFailure', 'profilecalculator.messages.retrieveFailure'])
                .then(successCallback);

            function successCallback(translations) {
                messages.profileUpdatedSuccess = translations['profilecalculator.messages.updatedSuccess'];
                messages.profileUpdatedFailure = translations['profilecalculator.messages.updatedFailure'];
                messages.profileRetrieveFailure = translations['profilecalculator.messages.retrieveFailure'];
            }

        }

        function getMineProfile() {
            return $http.get('/api/profilemine')
                .then(getProfileComplete)
                .catch(getProfileFailed);

            function getProfileFailed(response) {
                if (response.status == '404') {
                    return profileModel;
                }
                exception.catcher('XHR Failed for profile data')(message);
            }

            function getProfileComplete(response) {
                return response.data;
            }
        }

        function createProfile(profile) {
            return $http.post('/api/profiles/', profile)
                .then(createProfileComplete)
                .catch(createProfileFailed);

            function createProfileComplete(response) {
                toaster.pop('success', '', messages.profileUpdatedSuccess);
                return response.data;
            }

            function createProfileFailed(response) {
                exception.catcher(messages.profileUpdatedFailure)(response);
            }
        }

        function updateProfile(profile) {
            return $http.put('/api/profiles/', profile)
                .then(updateProfileComplete)
                .catch(updateProfileFailed);

            function updateProfileComplete(response) {
                toaster.pop('success', '', messages.profileUpdatedSuccess);
                return response.data;
            }

            function updateProfileFailed(response) {
                exception.catcher(messages.profileUpdatedFailure)(response);
            }
        }
    }

})();

