(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('profileDataservice', profileDataservice);

    /* @ngInject */
    function profileDataservice($http, $location, exception) {

        var service = {
            getProfile: getProfile,
            createProfile: createProfile,
            updateProfile: updateProfile,
            deleteProfile: deleteProfile
        };

        return service;

        function getProfile(profileId) {
            return $http.get('/api/profiles/' + profileId)
                .then(getProfileComplete)
                .catch(function(message) {
                    exception.catcher('XHR Failed for profile data')(message);
                    $location.url('/');
                });

            function getProfileComplete(data, status, headers, config) {
                return data;
            }
        }

        function createProfile(profile) {
            return $http.post('/api/profiles/', profile)
                .then(createProfileComplete)
                .catch(function(message) {
                    exception.catcher('XHR Failed for profile data')(message);
                    $location.url('/');
                });

            function createProfileComplete(data, status, headers, config) {
                return data;
            }
        }

        function updateProfile(profile) {
            return $http.put('/api/profiles/', profile)
                .then(updateProfileComplete)
                .catch(function(message) {
                    exception.catcher('XHR Failed for profile data')(message);
                    $location.url('/');
                });

            function updateProfileComplete(data, status, headers, config) {
                return data;
            }
        }

        function deleteProfile(){

        }
    }

})();

