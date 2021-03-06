'use strict';

angular.module('fitnowApp')
    .controller('MainController', function ($scope,$translate, Principal, AuthServerProvider) {

        // config
        $scope.app = {
            name: 'FitNow',
            version: '2.2.0',
            // for chart colors
            color: {
                primary: '#7266ba',
                info:    '#23b7e5',
                success: '#27c24c',
                warning: '#fad733',
                danger:  '#f05050',
                light:   '#e8eff0',
                dark:    '#3a3f51',
                black:   '#1c2b36'
            },
            settings: {
                themeID: 1,
                navbarHeaderColor: 'bg-black',
                navbarCollapseColor: 'bg-white-only',
                asideColor: 'bg-black',
                headerFixed: true,
                asideFixed: false,
                asideFolded: false,
                asideDock: false,
                container: false
            }
        };

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;


        });


        $scope.$watch('app.settings', function(){
            if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
                // aside dock and fixed must set the header fixed.
                $scope.app.settings.headerFixed = true;
            }
            // for box layout, add background image
            $scope.app.settings.container ? angular.element('html').addClass('bg') : angular.element('html').removeClass('bg');
            // save to local storage
            //$localStorage.settings = $scope.app.settings;
        }, true);

        // angular translate
        $scope.lang = { isopen: false };
        $scope.langs = {en:'English', pl:'Polski'};
        $scope.selectLang = $scope.langs[$translate.use()] || "English";
        $scope.setLang = function(langKey, $event) {
            // set the current lang
            $scope.selectLang = $scope.langs[langKey];
            // You can change the language during runtime
            $translate.use(langKey);
            $scope.lang.isopen = !$scope.lang.isopen;
        };

        $scope.logout = function(){
            AuthServerProvider.logout();
        };
        function isSmartDevice( $window )
        {
            // Adapted from http://www.detectmobilebrowsers.com
            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        }

    });

