(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('profileDataservice', profileDataservice);

    /* @ngInject */
    function profileDataservice($http, $location, exception, profileModel, toaster) {

        var service = {
            getMineProfile: getMineProfile,
            createProfile: createProfile,
            updateProfile: updateProfile,
            deleteProfile: deleteProfile
        };

        return service;

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
                toaster.pop('success', '', 'Profile updated');
                return response.data;
            }

            function createProfileFailed(response){
                exception.catcher('Update profile failed')(response);
            }
        }

        function updateProfile(profile) {
            return $http.put('/api/profiles/', profile)
                .then(updateProfileComplete)
                .catch(updateProfileFailed);

            function updateProfileComplete(response) {
                toaster.pop('success', '', 'Profile updated');
                return response.data;
            }

            function updateProfileFailed(response){
                exception.catcher('Update profile failed')(response);
            }
        }

        function deleteProfile() {

        }
    }

})();
