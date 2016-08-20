/* recommended */
/* spinner.directive.js */

/**
 * @desc spinner directive that can be used anywhere across apps at a company named Acme
 * @example <div acme-shared-spinner></div>
 */
angular
    .module('fitnowApp.directives')
    .directive('dirtyFormCheck', dirtyFormCheck);

function dirtyFormCheck() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            angular.element(element).areYouSure();
        }
    };
}
