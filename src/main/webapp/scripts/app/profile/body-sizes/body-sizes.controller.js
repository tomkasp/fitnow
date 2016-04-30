(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('BodySizeController', BodySizeController);

    /* @ngInject */
    function BodySizeController(bodySizesService, toaster) {
        var vm = this;
        vm.saveSizes = saveSizes;
        activate();


        ////////////////

        function activate() {
            return getBodySizes();
        }

        function getBodySizes(){
            return bodySizesService.getBodySizes().then(function(data){
                vm.size = data;
                return vm.size;
            });
        }

        function saveSizes(){
            toaster.pop('success', 'title', 'text');
            return bodySizesService.saveBodySizes(vm.size).then(function(data){
                 vm.size = data;
             });
        }
    }

})();

