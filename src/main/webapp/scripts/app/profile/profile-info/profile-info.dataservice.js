(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .service('profileInfoDataservice', profileInfoDataservice);


    /* @ngInject */
    function profileInfoDataservice($http, $location, exception, toaster) {
        this.getProfileInfoData = getProfileInfoData;

        ////////////////

        function getProfileInfoData() {
            return $http.get('/api/profileinfo')
                .then(getProfileInfoCompleted)
                .catch(getProfileInfoFailed);

            function getProfileInfoFailed(response){
                if(response.status == '404'){
                    return {}
                }
            }

            function getProfileInfoCompleted(response) {
                return response.data;
            }
        }
    }

})();

