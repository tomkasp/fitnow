(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('ProfileInfoController', ProfileInfoController);


    /* @ngInject */
    function ProfileInfoController(profileInfoDataservice, loadData, logger) {
        var vm = this;


        activate();

        ////////////////

        function activate() {
                vm.facebookImgUrl = loadData.facebookImgUrl + "?type=normal";
                vm.fullName = loadData.fullName;
                vm.location = loadData.location;
                vm.facebookProfileUrl = loadData.facebookProfileUrl;
        }
    }

})();

