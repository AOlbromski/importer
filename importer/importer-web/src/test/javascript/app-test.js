/* eslint "strict": [2, "global"], "global-strict": 0 */
'use strict';

angular.module('importerApp', [
    'ngResource',
    'ngMessages',
    'ngCookies',
    'ngCacheBuster',
    'ngAnimate',
    'ui.router',
    'ui.bootstrap',
    'angular-growl',
    'tmh.dynamicLocale',
    'infinite-scroll'
])

    .run(function ($rootScope, $location, $window, $http, $state) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            var publicStates = ['login', 'accessdenied'];
            if (!_(publicStates).includes(toState.name)) {
                //Auth.authorize();
            }
        });

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

            $window.document.title = (toState.data && toState.data.pageTitle) || 'SWHR';
        });
    })

    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

        // enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        $urlRouterProvider.otherwise('/');
    })

    .config(function (httpRequestInterceptorCacheBusterProvider, $httpProvider) {
        // Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api34.*/, /.*protected.*/], true);

    })

    /*
     * UI-related directives' configuration
     */
    .config(function (growlProvider) {
        growlProvider.globalTimeToLive(5000);
        growlProvider.globalDisableCountDown(true);
    })
    .config(function ($modalProvider) {
        // FIXME Until the issue https://github.com/angular-ui/bootstrap/issues/3633 is resolved
        $modalProvider.options.animation = false;
        $modalProvider.options.backdrop = false;
    })
    .run(['$rootScope', '$modalStack', function ($rootScope, $modalStack) {
        $rootScope.$on('$locationChangeStart', function () {
            var top = $modalStack.getTop();
            if (top) {
                $modalStack.dismiss(top.key);
            }
        });
    }]);
