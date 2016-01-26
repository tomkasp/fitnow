'use strict';

angular.module('fitnowApp').controller('MealDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Meal', 'Product',
        function($scope, $stateParams, $uibModalInstance, entity, Meal, Product) {

        $scope.meal = entity;
        $scope.products = Product.query();
        $scope.load = function(id) {
            Meal.get({id : id}, function(result) {
                $scope.meal = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('fitnowApp:mealUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.meal.id != null) {
                Meal.update($scope.meal, onSaveSuccess, onSaveError);
            } else {
                Meal.save($scope.meal, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
