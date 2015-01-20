// Karma configuration
// http://karma-runner.github.io/0.10/config/configuration-file.html

module.exports = function (config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: '../../',

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [
            // bower:js
            'main/webapp/bower_components/jquery/dist/jquery.js',
            'main/webapp/bower_components/modernizr/modernizr.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/affix.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/alert.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/button.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/carousel.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/collapse.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/dropdown.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/tab.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/transition.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/scrollspy.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/modal.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/tooltip.js',
            'main/webapp/bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/popover.js',
            'main/webapp/bower_components/angular/angular.js',
            'main/webapp/bower_components/json3/lib/json3.js',
            'main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js',
            'main/webapp/bower_components/angular-resource/angular-resource.js',
            'main/webapp/bower_components/angular-cookies/angular-cookies.js',
            'main/webapp/bower_components/angular-sanitize/angular-sanitize.js',
            'main/webapp/bower_components/angular-translate/angular-translate.js',
            'main/webapp/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
            'main/webapp/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js',
            'main/webapp/bower_components/angular-dynamic-locale/src/tmhDynamicLocale.js',
            'main/webapp/bower_components/angular-local-storage/dist/angular-local-storage.js',
            'main/webapp/bower_components/angular-cache-buster/angular-cache-buster.js',
            'main/webapp/bower_components/angular-mocks/angular-mocks.js',
            // endbower
            'main/webapp/scripts/app/app.js',
            'main/webapp/scripts/app/**/*.js',
            'main/webapp/scripts/components/**/*.js',
            'test/javascript/**/!(karma.conf).js'
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

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false
    });
};
