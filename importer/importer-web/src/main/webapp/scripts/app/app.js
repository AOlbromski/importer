/**
 * INSPINIA - Responsive Admin Theme
 * Copyright 2015 Webapplayers.com
 *
 */

/*
 'ui.router',                    // Routing
 'oc.lazyLoad',                  // ocLazyLoad
 'ui.bootstrap',                 // Ui Bootstrap
 'pascalprecht.translate',       // Angular Translate
 'ngIdle'                        // Idle timer
 */


(function () {

    angular.module('importerApp', [
        'LocalStorageModule',
        'tmh.dynamicLocale',
        'ngResource',
        'ui.router',
        'ngCookies',
        'pascalprecht.translate',
        'ngCacheBuster',
        'infinite-scroll',
        'oc.lazyLoad',
        'ui.bootstrap',
        'ngIdle',
        'fileReader',
        'ngLoadingSpinner',
        'datatables',
        'toastr'
    ]);

})();
