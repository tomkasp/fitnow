(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('BodySizeController', BodySizeController);

    /* @ngInject */
    function BodySizeController(bodySizesDataservice, lodash, logger) {
        var vm = this;
        vm.saveSizes = saveSizes;
        vm.data = [];
        vm.getBodySizesHistory = getBodySizesHistory;
        vm.change = change;
        //vm.selected = {};

        vm.bodyParts =
            [
                {id: 0, name: 'profilebodysize.FORM.arm', data: []},
                {id: 1, name: 'profilebodysize.FORM.waist', data: []},
                {id: 2, name: 'profilebodysize.FORM.thigh', data: []},
                {id: 3, name: 'profilebodysize.FORM.chest', data: []},
                {id: 4, name: 'profilebodysize.FORM.hip', data: []},
                {id: 5, name: 'profilebodysize.FORM.neck', data: []}
            ];

        activate();

        ////////////////

        function activate() {
            getBodySizesHistory();
            return getBodySizes();
        }

        function getBodySizes() {
            return bodySizesDataservice.getBodySizes().then(function (data) {
                vm.size = data;
                return vm.size;
            });
        }

        function getBodySizesHistory() {
            bodySizesDataservice.getBodySizesHistory().then(function (data) {
                lodash(data).forEach(function (value) {
                    var xVal = (new Date(value.date)).getTime();
                    // 1470528000000;//.parse(value.date);
                    var yArmVal = value.arm;
                    var yWaistVal = value.waist;
                    var yThighVal = value.thigh;
                    var yChestVal = value.chest;
                    var yHipVal = value.hip;
                    var yNeckVal = value.neck;
                    vm.bodyParts[0].data.push([xVal, yArmVal]);
                    vm.bodyParts[1].data.push([xVal, yWaistVal]);
                    vm.bodyParts[2].data.push([xVal, yThighVal]);
                    vm.bodyParts[3].data.push([xVal, yChestVal]);
                    vm.bodyParts[4].data.push([xVal, yHipVal]);
                    vm.bodyParts[5].data.push([xVal, yNeckVal]);

                    vm.selected = vm.bodyParts[0]; //setting default selected
                    vm.data = vm.bodyParts[0].data;
                });
            });
        }

        function saveSizes() {
            return bodySizesDataservice.saveBodySizes(vm.size).then(function (data) {
                vm.size = data;
            });
        }

        function change() {
            vm.data = vm.bodyParts[vm.selected.id].data;
        }
    }
})();

