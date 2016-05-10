(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .factory('bodySizesService', bodySizesService);

    /* @ngInject */
    function bodySizesService($http, $location, exception) {
        var service = {
            getBodySizes: getBodySizes,
            saveBodySizes: saveBodySizes

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
                .catch(function (message) {
                    exception.catcher('XHR Failed create body size')(message);
                    $location.url('/');
                });

            function createBodySizeCompleted(response) {
                return response.data;
            }
        }

        function updateBodySizes(bodySize) {
            return $http.put('/api/bodysizes', bodySize)
                .then(createBodySizeCompleted)
                .catch(function (message) {
                    exception.catcher('XHR Failed update body size')(message);
                    $location.url('/');
                });

            function createBodySizeCompleted(response) {
                return response.data;
            }
        }
    }

})();
