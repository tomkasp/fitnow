(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('BodySizeController', BodySizeController);

    /* @ngInject */
    function BodySizeController(bodySizesDataservice, toaster) {
        var vm = this;
        vm.saveSizes = saveSizes;
        activate();


        ////////////////

        function activate() {
            return getBodySizes();
        }

        function getBodySizes(){
            return bodySizesDataservice.getBodySizes().then(function(data){
                vm.size = data;
                return vm.size;
            });
        }

        function saveSizes(){
            return bodySizesDataservice.saveBodySizes(vm.size).then(function(data){
                 vm.size = data;
             });
        }
    }

})();

