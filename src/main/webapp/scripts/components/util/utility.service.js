(function () {
    'use strict';

    angular
        .module('fitnowApp')
        .service('Utility', Utility);

    Utility.$inject = [];

    /* @ngInject */
    function Utility() {
        this.nullToEmptyString = nullToEmpty;
        this.checkIfUndefined = checkIfUndefined;
        this.checkIfUndefinedOrNull = checkIfUndefinedOrNull;
        this.checkIfUndefinedOrNullOrEmpty = checkIfUndefinedOrNullOrEmpty;

        //////Functions definition///////

        function nullToEmpty(inputString) {
            if (_.isNull(inputString)) {
                return "";
            } else {
                return inputString;
            }
        }

        function checkIfUndefined(inputObject) {
            if (typeof inputObject === "undefined") {
                return true;
            }
            return false;
        }

        function checkIfUndefinedOrNull(inputObject) {
            if (typeof inputObject === "undefined" || inputObject === null) {
                return true;
            }
            return false;
        }

        function checkIfUndefinedOrNullOrEmpty(inputObject) {
            if (typeof inputObject === "undefined" || inputObject === null || inputObject === "") {
                return true;
            }
            return false;
        }
    }

})();
