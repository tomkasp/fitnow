'use strict';

angular.module('fitnowApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


