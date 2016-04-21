var fn = function ($scope, $translate, Language) {

    Language.getAll().then(function (languages) {
        $scope.languages = languages;
    });

    $scope.changeLanguage = function (languageKey) {
        $translate.use(languageKey);
    };
};

angular.module('importerApp').controller('LanguageController', fn);
