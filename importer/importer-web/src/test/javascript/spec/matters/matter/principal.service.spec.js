describe('Principal Service', function () {

    var principalService, http;

    beforeEach(function () {
        module('importerApp');
        inject(function ($httpBackend, _Principal_) {
            principalService = _Principal_;
            http = $httpBackend;
        });

    });
    it('Retrieve the identity data from the server', function () {
        http.expectGET('api/account').respond(201);

        principalService.identity(false);

        http.flush();

        expect(principalService.isAuthenticated()).toEqual(true);
    });

});
