angular.module('importerApp').controller('MainController', function ($scope, Principal) {

    $scope.isLoged = Principal.isAuthenticated;

});
