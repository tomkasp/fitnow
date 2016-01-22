 'use strict';

angular.module('fitnowApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-fitnowApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-fitnowApp-params')});
                }
                return response;
            }
        };
    });
