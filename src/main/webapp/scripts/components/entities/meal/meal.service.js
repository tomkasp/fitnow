'use strict';

angular.module('fitnowApp')
    .factory('Meal', function ($resource, DateUtils) {
        return $resource('api/meals/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
