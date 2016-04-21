angular.module('importerApp').factory('Language', function ($q, $http, $translate) {

    var languages = ['en', 'pl'];

    return {
        getCurrent: function () {
            var deferred = $q.defer();
            var language = $translate.storage().get('NG_TRANSLATE_LANG_KEY');

            if (angular.isUndefined(language)) {
                language = 'en';
            }

            deferred.resolve(language);
            return deferred.promise;
        },
        getAll: function () {
            var deferred = $q.defer();
            deferred.resolve(languages);
            return deferred.promise;
        }
    };
});
