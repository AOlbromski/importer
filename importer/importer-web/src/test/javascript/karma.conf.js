// Karma configuration
// http://karma-runner.github.io/0.10/config/configuration-file.html

module.exports = function (config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: '../../../',

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [

            // bower:js
            'src/main/webapp/bower_components/modernizr/modernizr.js',
            'src/main/webapp/bower_components/jquery/dist/jquery.js',
            'src/main/webapp/bower_components/bootstrap/dist/js/bootstrap.js',
            'src/main/webapp/bower_components/json3/lib/json3.js',
            'src/main/webapp/bower_components/angular/angular.js',
            'src/main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js',
            'src/main/webapp/bower_components/angular-resource/angular-resource.js',
            'src/main/webapp/bower_components/angular-cookies/angular-cookies.js',
            'src/main/webapp/bower_components/angular-sanitize/angular-sanitize.js',
            'src/main/webapp/bower_components/angular-dynamic-locale/src/tmhDynamicLocale.js',
            'src/main/webapp/bower_components/angular-cache-buster/angular-cache-buster.js',
            'src/main/webapp/bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js',
            'src/main/webapp/bower_components/angular-messages/angular-messages.js',
            'src/main/webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
            'src/main/webapp/bower_components/angular-animate/angular-animate.js',
            'src/main/webapp/bower_components/angular-growl-v2/build/angular-growl.js',
           //  'src/main/webapp/bower_components/moment/moment.js',
           //  'src/main/webapp/bower_components/lodash/lodash.js',
           // 'src/main/webapp/bower_components/angular-ui-select/dist/select.js',
           // 'src/main/webapp/bower_components/moment-duration-format/lib/moment-duration-format.js',
           //  'src/main/webapp/bower_components/checklist-model/checklist-model.js',
           // 'src/main/webapp/bower_components/ng-file-upload/ng-file-upload.js',
           // 'src/main/webapp/bower_components/ng-file-upload-shim/ng-file-upload-shim.js',
           // 'src/main/webapp/bower_components/ngstorage/ngStorage.js',
            'src/main/webapp/bower_components/angular-mocks/angular-mocks.js',
         //   'src/main/webapp/bower_components/uri.js/src/URI.min.js'
            // endbower
            <!-- inject:js -->

            'src/test/javascript/app-test.js',

            '../importer-security/src/main/resources/static/scripts/app/common/auth/service/account.service.js',
            '../importer-security/src/main/resources/static/scripts/app/common/auth/service/principal.service.js',
            'src/main/webapp/scripts/app/common/common.controller.js',

            <!-- endinject -->
           // 'src/test/javascript/swhr.test.state.js',
            'src/test/javascript/spec/**/*.js',
        ],

        // list of files / patterns to exclude
        exclude: [],

        // web server port
        port: 9876,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        preprocessors: {
            'src/main/webapp/scripts/**/*.js': ['coverage']
        },

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: true
    });
};
