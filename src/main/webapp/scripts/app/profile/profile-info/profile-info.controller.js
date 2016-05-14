(function () {
    'use strict';

    angular
        .module('fitnowApp.profile')
        .controller('ProfileInfoController', ProfileInfoController);


    /* @ngInject */
    function ProfileInfoController(profileInfoDataservice, logger) {
        var vm = this;
        vm.facebookImgUrl = "";

        activate();

        ////////////////

        function activate() {
            profileInfoDataservice.getProfileInfoData().then(function(data){
                vm.facebookImgUrl = data.facebookImgUrl + "?type=normal";
            });
        }
    }

})();

