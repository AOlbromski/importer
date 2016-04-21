describe('CommonController', function () {

    var http, scope, rootScope, controller, principalService;

    beforeEach(function () {
        module('importerApp');
    });

    beforeEach(inject(function ($httpBackend, $controller, $rootScope, _Principal_) {
        http = $httpBackend;
        rootScope = $rootScope;
        controller = $controller;
        principalService = _Principal_;
    }));


    function initializeController() {
        scope = rootScope.$new();
        controller('CommonController', {
            $scope: scope,
            Principal: principalService
        });
    }

    it('check user is authenticated', function () {

        http.expectGET('api/account').respond(201);

        initializeController();
        rootScope.$apply();

        http.flush();

        expect(scope.isAuthenticated()).toEqual(true);
    });

});
