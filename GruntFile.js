module.exports = function (grunt) {
    //grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-compass');
    grunt.loadNpmTasks('grunt-browser-sync');
    grunt.loadNpmTasks('grunt-sync');
    grunt.loadNpmTasks('grunt-karma');
    grunt.initConfig({
        /*uglify: {
         my_target: {
         files: {
         '_/js/script.js': ['src/main/webapp/js/*.js']
         } //files
         } //my_target
         }, //uglify*/
        compass: {
            dev: {
                cssDir: 'src/main/webapp/css',
                sassDir: 'src/main/sass',
                javascriptsDir: 'src/main/webapp/app',
                outputStyle: ':expanded',
                relativeAssets: true
            } //options
        }, //compass
        // karma: {
        //     unit: {
        //         configFile: 'src/test/javascript/unit/karma.conf.js',
        //         browsers: ['PhantomJS'],
        //         background: true,
        //         singleRun: false
        //     },
        //     integration: {
        //         configFile: 'src/test/javascript/integration/karma.conf.js',
        //         browsers: ['PhantomJS'],
        //         background: true,
        //         singleRun: false
        //     }
        // }, // karma
        browserSync: {
            bsFiles: {
                src: [
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/css/main.css',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/*.*html',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/WEB-INF/templates/*',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/app/**/*.*',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/app/*.*',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/email/*',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/WEB-INF/classes/*.properties',
                    'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/WEB-INF/classes/**/*.xhtml'
                ]
            },
            options: {
                watchTask: true,
                proxy: 'localhost:8080',
                ghostMode: false,
                startPath: '/index.html'
            }
        }, // browserSync
        watch: {
            //options: { livereload: true },
            /*scripts: {
             files: ['src/main/webapp/js/*.js'],
             tasks: ['uglify']
             },//scripts*/
            sass: {
                files: ['src/main/sass/*.scss', 'src/main/sass/**/*.scss'],
                tasks: ['compass:dev']
            }, //sass
            static: {
                files: [
                    'src/main/webapp/*.*html',
                    'src/main/webapp/**/*.*html',
                    'src/main/webapp/css/main.css',
                    'src/main/webapp/app/*.js',
                    'src/main/webapp/app/**/*.js',
                    'src/test/javascript/*.js',
                    'src/test/javascript/**/*.js',
                    'src/main/resources/*.properties',
                    'src/main/resources/**/*.xhtml'
                ],
                tasks: ['sync']
            }, //static
            // unittests: {
            //     files: [
            //         'src/main/webapp/app/*.js',
            //         'src/main/webapp/app/**/*.js',
            //         'src/test/javascript/unit/*.js',
            //         'src/test/javascript/unit/**/*.js'
            //     ],
            //     tasks: ['karma:unit:run']
            // }, //tests
            // integrationtests: {
            //     files: [
            //         'src/test/javascript/integration/*.js',
            //         'src/test/javascript/integration/**/*.js'
            //     ],
            //     tasks: ['karma:integration:run']
            // } //tests
        }, //watch
        sync: {
            copy_updated_resources_to_autodeploy: {
                files: [
                    {
                        cwd: 'src/main/webapp/',
                        src: [
                            'css/main.css',
                            '**/*.*html',
                            '*.*html',
                            '**/*.js',
                            '*.js'
                        ],
                        dest: 'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war'
                    },
                    {
                        cwd: 'src/main/resources/',
                        src: [
                            '*.properties',
                            '**/*.xhtml'
                        ],
                        dest: 'classes/artifacts/ESAlertRoster/exploded/ESAlertRoster.war/WEB-INF/classes'
                    }
                ]
            }
        } // sync
    }); //initConfig
    grunt.registerTask('default', ['compass:dev', 'sync', 'browserSync', 'watch']);//, 'karma:unit', 'karma:integration'
} //exports
