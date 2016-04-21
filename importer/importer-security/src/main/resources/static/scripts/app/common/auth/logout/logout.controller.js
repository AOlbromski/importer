angular.module('importerApp').controller('LogoutController', function ($scope, $state, Auth) {

    $scope.logout = function () {
        Auth.logout();
        $state.go('home');
    };
});
