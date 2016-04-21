// Generated on 2015-03-16 using generator-jhipster 2.6.0
/* eslint-disable */
'use strict';

var gulp = require('gulp'),
    $ = require('gulp-load-plugins')(),
    proxy = require('proxy-middleware'),
    es = require('event-stream'),
    del = require('del'),
    url = require('url'),
    wiredep = require('wiredep').stream,
    fs = require('fs'),
    runSequence = require('run-sequence'),
    browserSync = require('browser-sync'),
    path = require('path'),
    autoprefixer = require('autoprefixer-core');

var yeoman = {
    app: 'src/main/webapp/',
    dist: 'target/webapp-dist/',
    test: 'src/test/javascript/spec/',
    app_security: '../importer-security/src/main/resources/static/',
    tmp: 'target/webapp-generated/src/test/resources/static/',
    port: 9000,
    apiPort: 8080,
    liveReloadPort: 35729
};

var parseString = require('xml2js').parseString;
var parseVersionFromPomXml = function () {
    var version;
    var pomXml = fs.readFileSync('pom.xml', 'utf8');
    parseString(pomXml, function (err, result) {
        version = result.project.version[0];
    });
    return version;
};

//gulp.task('bower:install', $.bower);

gulp.task('eslint:app', function () {
    return eslint(yeoman.app + 'scripts/**/*.js');
});

gulp.task('eslint:test', function () {
    return eslint(yeoman.test + '**/*.js');
});

gulp.task('eslint:app-security', function () {
    return eslint(yeoman.app_security + '**/*.js');
});

gulp.task('eslint', function () {
    runSequence('eslint:app', 'eslint:app-security', 'eslint:test');
});

gulp.task('karma:run', function (done) {
    var karma = require('karma').server;
    karma.start({
        configFile: path.join(__dirname, 'src', 'test', 'javascript', 'karma.conf.js'),
        singleRun: true,
        reporters: ['dots', 'junit', 'coverage'],
        junitReporter: {
            outputFile: 'target/surefire-reports/web-results.xml',
            suite: ''
        },
        coverageReporter: {
            dir: 'target/webapp-coverage',
            reporters: [
                {type: 'html'},
                {type: 'cobertura', subdir: '.', file: 'cobertura.txt'}
            ]
        }
    }, done);
});

function eslint(src) {
    return gulp.src(src)
        .pipe($.eslint())
        .pipe($.eslint.format())
        .pipe($.eslint.failAfterError());
}

gulp.task('default', ['build']);
/* eslint-enable */
